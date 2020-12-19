#define FALSE 0
#define TRUE 1


#include <iostream>
#include <unistd.h>
#include <pthread.h>
#include <time.h>
#include <stdlib.h>


using namespace std;

const int M_numofcols = 50;
const int M_numofrows = 2;
int remaining_seats = 100; // i keep number of seats to track it easily
bool turn = 0; // this is a variable for busy waiting
int ** array_2D = new int * [M_numofrows]; // creating giving decleration for  2d dynamic array


void * Travel_Agency1(void * ) // this is a function for agency1 thread to access
{
	while(remaining_seats != 0 ) // instead of while true i iterate until the plane is full
	{
		while(turn != 0); // busy wait if not your turn
		int rn = rand() % 100; // create a random number between including 0 - 99
		if(rn <= 49) // this is for the right side of the plane and corresponding indexes
		{
			if(array_2D[0][rn] == 0) // check if the seat in the right side is empty
			{
				cout << "Agency " << 1 << " Entered Critical Region " << endl;
				array_2D[0][rn] = 1; // make reservation
				remaining_seats = remaining_seats - 1; // decrement free seat number
				cout << "Seat Number " << rn + 1 << " is reserved by Agency " << 1 << endl; // rn + 1 is there because seat number starts from 0 and ends with 100
				cout << "Agency " << 1 << " Exit Critical Region " << endl;				
				turn = 1; // change the boolean variable to enable other thread to enter
			}
		}
		else // this is for the left sde of the plane when generated number is bigger than 49
		{
			if(array_2D[1][rn - 50] == 0) // rn-50 is there to make convertions between seat numbers and indexes
			{
				cout << "Agency "<< 1<< " Entered Critical Region " << endl;
				array_2D[1][rn - 50] = 1;
				remaining_seats = remaining_seats - 1;
				cout << "Seat Number " << rn + 1 << " is reserved by Agency " << 1 << endl;
				cout << "Agency " << 1 << " Exit Critical Region " << endl;			
				turn = 1;
			}
		}
	}
}


void * Travel_Agency2(void * ) // this is same logic with above method, only this is for thread2 to access
{
	while(remaining_seats != 0 )
	{
		while(turn != 1);
		int rn = rand()% 100;
		if(rn <= 49)
		{
			if(array_2D[0][rn] == 0)
			{
				cout << "Agency "<< 2 << " Entered Critical Region " << endl;
				array_2D[0][rn] = 2;
				remaining_seats = remaining_seats - 1;
				cout << "Seat Number "<< rn + 1 << " is reserved by Agency " << 2 << endl;
				cout << "Agency " << 2 << " Exit Critical Region " << endl;				
				turn = 0;
			}
		}
		else
		{
			if(array_2D[1][rn - 50] == 0)
			{
				cout << "Agency "<< 2 << " Entered Critical Region " << endl;
				array_2D[1][rn - 50] = 2;
				remaining_seats = remaining_seats - 1;
				cout << "Seat Number "<< rn + 1 << " is reserved by Agency " << 2 << endl;
				cout << "Agency " << 2 << " Exit Critical Region " << endl;
				turn = 0;
			}
		}
	}
}


int main()
{
	srand(time(NULL)); // to generate random each time
	for(int i = 0; i < M_numofrows; i++) // initializing the matrix(plane) with 0 in these for loops
	{
	array_2D[i] = new int[M_numofcols];
	}
	for(int i = 0; i < M_numofrows; i++)
	{
		for(int j = 0; j < M_numofcols; j++)
		{
			array_2D[i][j] = 0;
		}
	}
	
	
	pthread_t TravelAgency1; // define thread
	pthread_t TravelAgency2;
	
	
	pthread_create(&TravelAgency1,NULL,Travel_Agency1, NULL); // creating the threads
	pthread_create(&TravelAgency2,NULL,Travel_Agency2, NULL);
	
	
	
	pthread_join(TravelAgency1,NULL); // joining the threads when calculations are done
	pthread_join(TravelAgency2,NULL);
	
	
	
	if(remaining_seats == 0) // when there are no more seats left print the schema of the plane
	{
		cout << "No Seats Left" << endl;
		cout << "Plane is full: " << endl;
		for(int i = 0; i < M_numofrows;i++)
		{
			for(int j = 0; j < M_numofcols;j++)
			{
				cout << array_2D[i][j];
			}
			cout << endl;
		}
	}
	
	for(int i = 0; i < M_numofrows;i++) // free memory locations from dynamically allocated 2d array
	{
		delete [] array_2D[i];
	}
	delete [] array_2D; // prevent memory leakage
	
	return 0;
}

