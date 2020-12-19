// Emre Demirci,26531

#include <iostream>
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <string>
#include <stdlib.h> 
#include <queue> 
#include <semaphore.h>
using namespace std;

#define NUM_THREADS 10
#define MEMORY_SIZE 150

struct node
{
	int id;
	int size;
};


queue<node> myqueue; // shared que
pthread_mutex_t sharedLock = PTHREAD_MUTEX_INITIALIZER; // mutex
pthread_t server; // server thread handle
sem_t semlist[NUM_THREADS]; // thread semaphores

int thread_message[NUM_THREADS]; // thread memory information
char  memory[MEMORY_SIZE]; // memory size

void my_malloc(int thread_id, int size)
{
	//This function will add the struct to the queue
	if(size > 0 && size < 150)
	{
	    
		node element;
		element.size = size;
		element.id = thread_id;

		// lock the because entering critical region
		pthread_mutex_lock(&sharedLock); 
		myqueue.push(element);
		// release lock
		pthread_mutex_unlock(&sharedLock);
	}
	else
	{
		try
		{
			node element;
			element.size = size;
			element.id = thread_id;

			// lock the because entering critical region
			pthread_mutex_lock(&sharedLock); 
			myqueue.push(element);

			// release lock
			pthread_mutex_unlock(&sharedLock);
		}
		catch(...)
		{
			cout << "Exception occured size can not be negative or it exceeds memory size" << endl;
		}
	}
}

bool all_threads_are_done = false;
int idx = 0; // dynamic index
void * server_function(void *)
{
	//This function should grant or decline a thread depending on memory size.
	while(all_threads_are_done == false)
	{
		//lock it so we do not get ruined
		pthread_mutex_lock(&sharedLock);
		if(myqueue.empty() == false)
		{
			if(myqueue.front().size < MEMORY_SIZE - idx) // not empty and smaller than grant access
			{
				thread_message[myqueue.front().id] = idx;
				idx = idx + myqueue.front().size;
			}
			else
			{
				thread_message[myqueue.front().id] = -1;
			}
			sem_post(&semlist[myqueue.front().id]); // semaphore up
			myqueue.pop();
		}
		else
		{
		}
		pthread_mutex_unlock(&sharedLock); 
	}
}

void * thread_function(void * id) 
{
	//This function will create a random size, and call my_malloc
	//Block
	//Then fill the memory with id's or give an error prompt
	int limit = MEMORY_SIZE/6;
	int rn = rand() %limit + 1; // 1<= x <= limit
	void *ptr = malloc(sizeof(int));
	int id_num = *((int*)id); // cast void ptr to int value
	my_malloc(id_num,rn);
	sem_wait(&semlist[id_num]); //down
	if(thread_message[id_num] == -1)
	{
		pthread_mutex_lock(&sharedLock);
		cout << "Thread " << id_num << ": " << "Not enough memory"<< endl;  
		pthread_mutex_unlock(&sharedLock);
	}
	else
	{
		for(int i= 0; i < rn; i++)
		{
			// idnum = idnum  +0
			// other index is idnum+1 which is i in the second iteration
			if(i + thread_message[id_num] <= MEMORY_SIZE) // max limit incase if anyone changes thread numbers
			{
				memory[i + thread_message[id_num]] = id_num;
			}
			else
			{
				cout << "Problem might be about: Thread numbers are too large, unallocated memory access " << endl;
			}
		}
	}
}

void init()	 
{
	pthread_mutex_lock(&sharedLock);	//lock
	for(int i = 0; i < NUM_THREADS; i++) //initialize semaphores
	{sem_init(&semlist[i],0,0);}
	for (int i = 0; i < MEMORY_SIZE; i++)	//initialize memory 
  	{char zero = '0'; memory[i] = zero;}
   	pthread_create(&server,NULL,server_function,NULL); //start server 
	pthread_mutex_unlock(&sharedLock); //unlock
}



void dump_memory() 
{
    // You need to print the whole memory array here.
	pthread_mutex_lock(&sharedLock);
	for(int m = 0; m < MEMORY_SIZE; m++)
	{
		printf("%d",memory[m]);
	}
	pthread_mutex_unlock(&sharedLock);
}

int main (int argc, char *argv[])
 {
	 srand(time(NULL)); // to generate random each time
 	 //You need to create a thread ID array here

	 pthread_t t_array[NUM_THREADS];
	 for(int i = 0; i < NUM_THREADS;i++)
	 {
		 pthread_t virtual_new;
		 t_array[i] = virtual_new;
		// t_array[i] = new pthread_t(); did not work 
	 }

	 int i_array[NUM_THREADS];
	 for(int i = 0; i < NUM_THREADS;i++) // instead of creating this i tried writing index number in threadcreate did not work :/
	 {
		 i_array[i] = i;
	 }

 	init();	// call init

 	//You need to create threads with using thread ID array, using pthread_create()
	for(int i = 0; i < NUM_THREADS;i++)
	{
		pthread_create(&t_array[i],NULL,thread_function,(void*)&i_array[i]); 
	}
 	//You need to join the threads
	for(int i= 0; i< NUM_THREADS; i++)
	{
		pthread_join(t_array[i],NULL);
	}
	
	all_threads_are_done = true;

 	dump_memory(); // this will print out the memory
 	
 	printf("\nMemory Indexes:\n" );
 	for (int i = 0; i < NUM_THREADS; i++)
 	{
 		printf("[%d]" ,thread_message[i]); // this will print out the memory indexes
 	}
 	printf("\nTerminating...\n");
	
 }