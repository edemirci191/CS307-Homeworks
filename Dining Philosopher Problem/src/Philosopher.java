import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class Table {

	// FOR GUI:
	static JFrame frame; 
	private JLabel[] plates = new JLabel[5];
	private JLabel[] forks = new JLabel[5];
	static Table window;
	static BufferedImage fork;

	/**
	 * Launch the application.

	/**
	 * Create the application.
	 */
	public Table() {


		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		BufferedImage plate = null;
		try {
			plate = ImageIO.read(new File("spaghetti_yellow.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.getContentPane().setLayout(null);
		plates[0] = new JLabel(new ImageIcon(plate));
		plates[0].setBounds(185, 10, 100, 100);
		frame.getContentPane().add(plates[0]);
		plates[0].setVisible(false);

		plates[1] = new JLabel(new ImageIcon(plate));
		plates[1].setBounds(300, 100, 100, 100);
		frame.getContentPane().add(plates[1]);
		plates[1].setVisible(false);

		plates[2] = new JLabel(new ImageIcon(plate));
		plates[2].setBounds(230, 230, 100, 100);
		frame.getContentPane().add(plates[2]);
		plates[2].setVisible(false);

		plates[3] = new JLabel(new ImageIcon(plate));
		plates[3].setBounds(70, 210, 100, 100);
		frame.getContentPane().add(plates[3]);
		plates[3].setVisible(false);

		plates[4] = new JLabel(new ImageIcon(plate));
		plates[4].setBounds(50, 80, 100, 100);
		frame.getContentPane().add(plates[4]);
		plates[4].setVisible(false);

		try {
			fork = ImageIO.read(new File("fork_white_1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		forks[0] = new JLabel(new ImageIcon(fork));
		forks[0].setBounds(250, 40, 100, 100);
		frame.getContentPane().add(forks[0]);

		try {
			fork = ImageIO.read(new File("fork_white_2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		forks[1] = new JLabel(new ImageIcon(fork));
		forks[1].setBounds(280, 170, 100, 100);
		frame.getContentPane().add(forks[1]);

		try {
			fork = ImageIO.read(new File("fork_white_3.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		forks[2] = new JLabel(new ImageIcon(fork));
		forks[2].setBounds(150, 220, 100, 100);
		frame.getContentPane().add(forks[2]);

		try {
			fork = ImageIO.read(new File("fork_white_4.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		forks[3] = new JLabel(new ImageIcon(fork));
		forks[3].setBounds(60, 145, 100, 100);
		frame.getContentPane().add(forks[3]);

		try {
			fork = ImageIO.read(new File("fork_white_5.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		forks[4] = new JLabel(new ImageIcon(fork));
		forks[4].setBounds(95, 15, 100, 100);
		frame.getContentPane().add(forks[4]);
	}


	public void PutPlate_GUI(int i)
	{
		plates[i].setVisible(true);
	}

	public void StartDining_GUI(int i)
	{
		try {
			plates[i].setIcon(new ImageIcon(ImageIO.read(new File("spaghetti_white.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Hungry_GUI(int i) {

		try {
			plates[i].setIcon(new ImageIcon(ImageIO.read(new File("spaghetti_red.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void ForkTake_GUI(int i) {
		try{
			if (i == 0)
			{
				forks[0].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_1.jpg"))));
				forks[4].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_5.jpg"))));
			}
			else if (i == 1)
			{
				forks[0].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_1.jpg"))));
				forks[1].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_2.jpg"))));
			}
			else if (i == 2)
			{
				forks[1].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_2.jpg"))));
				forks[2].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_3.jpg"))));
			}
			else if (i == 3)
			{
				forks[2].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_3.jpg"))));
				forks[3].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_4.jpg"))));
			}
			else if (i == 4)
			{
				forks[3].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_4.jpg"))));
				forks[4].setIcon(new ImageIcon(ImageIO.read(new File("fork_red_5.jpg"))));
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void Eating_GUI(int i) {
		try {
			plates[i].setIcon(new ImageIcon(ImageIO.read(new File("spaghetti_blue.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
	}

	public void ForkPut_GUI(int i) {
		try{
			if (i == 0)
			{
				forks[0].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_1.jpg"))));
				forks[4].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_5.jpg"))));
			}
			else if (i == 1)
			{
				forks[0].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_1.jpg"))));
				forks[1].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_2.jpg"))));
			}
			else if (i == 2)
			{
				forks[1].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_2.jpg"))));
				forks[2].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_3.jpg"))));
			}
			else if (i == 3)
			{
				forks[2].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_3.jpg"))));
				forks[3].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_4.jpg"))));
			}
			else if (i == 4)
			{
				forks[3].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_4.jpg"))));
				forks[4].setIcon(new ImageIcon(ImageIO.read(new File("fork_white_5.jpg"))));
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void StopEating_GUI(int i) {
		try {
			plates[i].setIcon(new ImageIcon(ImageIO.read(new File("spaghetti_white.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}


public class Philosopher extends Thread {

	private static Table table;

	private int THINKING = 0;
	private int HUNGRY = 1;
	private int EATING = 2;


	private Semaphore [] sem;
	private Semaphore[] barriers;
	private Semaphore mutex;
	private int id;


	private int [] state;
	private int N;

	public Philosopher(int i, Semaphore [] s, int [] mystate, int philnumber, Semaphore mu, Semaphore[] bar)
	{
		id = i;
		sem = s;
		state = mystate;
		N = philnumber;
		mutex = mu;
		barriers = bar;
	}

	
	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table = new Table();
					table.frame.setVisible(true);

				}
				catch(Exception e){
					e.printStackTrace();

				}
			}
		});


		int N = 5;
		int [] state = new int [N];


		Semaphore mutex = new Semaphore (1);
		Semaphore [] semarray = new Semaphore [N];
		Semaphore [] bararray = new Semaphore [N];

		Philosopher[] oldies = new Philosopher [N];

		for (int i=0; i < N; i++)
		{
			semarray[i] = new Semaphore(0,true);	
			bararray[i] = new Semaphore(0,true);
		}
		for (int i=0; i < N; i++)
		{
			oldies[i] = new Philosopher(i, semarray, state, N, mutex,bararray);
			oldies[i].start();
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void run() // Emre Demirci, 26531
	{
		int random_number = ThreadLocalRandom.current().nextInt(1,10);

		try
		{
			sleep(random_number*1000); // sleep between 1-10 sec
			table.PutPlate_GUI(id);

		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		try // wait for each philosopher to arrive to the table
		{
			int num;
			for(num = 0; num < 5; ++num)
			{
				if (this.id != num)
				{
					this.barriers[this.id].release();
				}
			}
			for(num = 0; num < 5; ++num)
			{
				if (this.id != num)
				{
					this.barriers[num].acquire();
				}
			}
		}
		catch (InterruptedException var2)
		{
			var2.printStackTrace();
		}

		// Dining
		table.StartDining_GUI(0);
		table.StartDining_GUI(1);
		table.StartDining_GUI(2);
		table.StartDining_GUI(3);
		table.StartDining_GUI(4);

		while(1 == 1) // now thats a philosophical one
		{
			//***************************** start thinking ***********************
			try
			{
				int random_number2 = ThreadLocalRandom.current().nextInt(1,10);
				sleep(random_number2*1000);
				table.Hungry_GUI(this.id);
			}
			catch (InterruptedException var3)
			{
				var3.printStackTrace();
			}
			//**************************** finish thinking **************************


			//*************************** take fork *********************************
			try
			{
				this.mutex.acquire();
			}
			catch (InterruptedException var3)
			{
				var3.printStackTrace();
			}
			this.state[this.id] = this.HUNGRY; // make this thread hungry
			if (this.state[this.id] == this.HUNGRY && this.state[(this.id + 4) % 5] != this.EATING && this.state[(this.id + 1) % 5] != this.EATING)
			{ // if left philosopher and right philosopher is not eating
				this.state[this.id] = this.EATING; // now you are eating
				table.ForkTake_GUI(this.id); // so you can take the forks
				this.sem[this.id].release(); // therefore release semephore.
			}
			this.mutex.release(); // exit critical region
			try
			{
				this.sem[this.id].acquire();
			}
			catch (InterruptedException var2)
			{
				var2.printStackTrace();
			}
			//*************************** finish take fork ******************************


			//*************************** start: after checks are done start eating ************
			table.Eating_GUI(this.id);
			//*************************** finish: after checks are done start eating ************


			// **************************** start : decide to put the fork down ******************
			try
			{
				this.mutex.acquire();
			}
			catch (InterruptedException var2)
			{
				var2.printStackTrace();
			}
			this.state[this.id] = this.THINKING;
			table.ForkPut_GUI(this.id);
			table.StopEating_GUI(this.id);
			//************************** finish: decide to put the fork down ************************


			//************************** start: because fork is put down, start eat ******************************
			// there is 2 if statement to prevent starvation
			// if the neighbour of his neighbour philosopher is not eating only then it can take the fork and start eating
			if (this.state[(this.id + 4) % 5] == this.HUNGRY && this.state[((this.id + 4) % 5 + 4) % 5] != this.EATING && this.state[((this.id + 4) % 5 + 1) % 5] != this.EATING)
			{
				this.state[(this.id + 4) % 5] = this.EATING; // if its neighbours are not eating then it can start eating
				table.ForkTake_GUI((this.id + 4) % 5); // take the fork because they must be free since other 2 not eating
				this.sem[(this.id + 4) % 5].release(); // because the other neighbour of this philosopher is not eating so leave this fork ( so he can eat)
			}
			// if the neighbour of his neighbour philosopher is not eating only then it can take the fork and start eating
			if (this.state[(this.id + 1) % 5] == this.HUNGRY && this.state[((this.id + 1) % 5 + 4) % 5] != this.EATING && this.state[((this.id + 1) % 5 + 1) % 5] != this.EATING)
			{
				this.state[(this.id + 1) % 5] = this.EATING; // similar to above part if its neighbours (left and right) is not eating it can start eating since forks must be free
				table.ForkTake_GUI((this.id + 1) % 5); // so take the fork
				this.sem[(this.id + 1) % 5].release(); // and release the sem
			}
			this.mutex.release();
			//**************************** finish: because fork is put down, start eat****************************
		}
	}
}


