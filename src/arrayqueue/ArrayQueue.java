package arrayqueue;
import java.util.*;

@SuppressWarnings("unchecked")
public class ArrayQueue<AnyType> implements  QueueInterface<AnyType>, Iterable<AnyType>
{
        static Scanner input = new Scanner(System.in);
	private static final int DEFAULT_CAPACITY = 10;
	private int cap,	// total number of elements in the queue
                  cur,		// current number of elements
                  front,  	// front index
                  back;		// back index
	private AnyType[] A;

	/**
	*  Creates a new empty queue.
	*/
	public ArrayQueue ()
	{
		cap = DEFAULT_CAPACITY;
		A = (AnyType[]) new Object[DEFAULT_CAPACITY];
		back = -1; front = 0;
	}

	/**
	*  Tests if the queue is logically empty.
	*
	*  @return true if the queue is empty and false otherwise
	*/
	public boolean isEmpty()
	{
		return cur == 0;
	}

	/**
	*  Puts a value into the back of the queue. It works with wraparound.
	*  If the queue is full, it doubles its size.
	*
	*  @param value the item to insert.
	*/
	public void enqueue (AnyType value)
	{
		if (isFull()) doubleSize();

		back++;
		A[back%cap] = value;
		cur++;
	}

	/**
	*  Returns the first element in the queue.
	*
	*  @return element at front of the queue
	*  @throws NoSuchElementException if the queue is empty.
	*/
	public AnyType getFront()
	{
		if (isEmpty())
			throw new QueueException();
		else
			return A[front%cap];
	}

	/**
	*  Returns and removes the front element of the queuee. It works with wraparound.
	*
	*  @return element at front of the queue
	*  @throws NoSuchElementException if the queue is empty.
	*/
	public AnyType dequeue()
	{
		AnyType e = getFront();
		A[front%cap] = null; // for garbage collection
		front++;
		cur--;
		return e;
	}

	/**
	*  Makes the queue physically empty.
	*
	*/
	public void clear()
	{
		for(int i = 0; i < cap; i++) A[i] = null;

		cur = 0; back = -1; front = 0;
	}

	/**
	*  Tests if the queue is logically full
	*/
	public boolean isFull()
	{
		return cur == cap;
	}
	private void doubleSize()
	{
		AnyType[] newArray = (AnyType[]) new Object[2*cap];

		
		for(int i = front; i <= back; i ++)
			newArray[i-front] = A[i%cap];

		A = newArray;
		front = 0;
		back = cur-1;
		cap *= 2;
	}



	/**
	* Obtains an Iterator object used to traverse the Queue from its front to back.
	*
	* @return an iterator.
	*
	* @throws UnsupportedOperationException if you remove using the iterator
	*/
	public Iterator<AnyType> iterator( )
	{
		return new QueueIterator( );
	}

	private class QueueIterator implements Iterator<AnyType>
	{
		private int index;      //traversal index


		/**
		*  Create a new empty iterator.
		*/
		public QueueIterator()
		{
			index = front;
		}

		/**
		*  Tests if there are more items in the Queue
		*
		*/
		public boolean hasNext( )
		{
			return index <= back;
		}

		/**
		*  Returns the next item in the Queue.
		*
		*/
		public AnyType next( )
		{
			return A[(index++)%cap];
		}

		/**
		*  Remove is not implemented
		*
		*/
		public void remove( )
		{
			throw new java.lang.UnsupportedOperationException();
		}
	}

	public static void main(String[] args)
	{
		ArrayQueue<String> Q = new ArrayQueue<String>();
                
                System.out.println("QUEUE OPERATION MENU");
                System.out.println("______________________");
                System.out.println("1.ENQUUE");
                System.out.println("2.DEQUEUE");
                System.out.println("3.EMPTY");
                System.out.println("4.FULL");
                System.out.println("5.VIEW QUEUE");
                
                System.out.println("Enter number of name you have on the list");
                int numberOfNames = input.nextInt();
                String[] people = new String[numberOfNames];
            
                System.out.println("Enter you choice:");
                int operation = input.nextInt();
                switch(operation)
                {
                    case 1:
                    {
                        for(int i = 0; i < people.length; i++)
                        {
                            System.out.println("Enter a name: ");
                            people[i] =input.next();
                         }
                        for (int i = 0; i < people.length; i++)
                        {
                            Q.enqueue(people[i]);
                        }
                        System.out.println("do you want to check if a queue is full?\n"
                                + "Type: 1. - yes\n"
                                + "\t2. - no");
                        int c = input.nextInt();
                        if(c == 2)
                        {
                           Q.isFull();
                         
                        }
                        else 
                        {
                        
                        }
                        
                         break;
                    }
                    case 2:
                    {
                        System.out.println("how many items do you want to deque?");
                        
                        int DItemNo = input.nextInt();
                         for (int i = 0; i <DItemNo; i++) 
                        {
                            Q.dequeue();
                        } 
                    }
                }
            
		Iterator itr = Q.iterator();
		while(itr.hasNext())
			System.out.println(itr.next());

		System.out.println("=================");

		Q.enqueue("Mike");
		Q.enqueue("Bev");

		itr = Q.iterator();
		while(itr.hasNext())
			System.out.print(itr.next() + " ");

		System.out.println();
   }
}
interface QueueInterface<AnyType>
{
	/**
	* Tests if the Queue is empty.
	*/
	public boolean isEmpty();

	/**
	*  Removes and returns the front item
	*/
	public AnyType dequeue() throws QueueException;

	/**
	*  Returns the front item without its removal
	*/
	public AnyType getFront() throws QueueException;

	/**
	* Inserts an item to teh back
	*/
	public void enqueue(AnyType e);

	/**
	* Removes all items from the Queue.
	*/
	public void clear();
}


	/**              QueueException           **/


class QueueException extends RuntimeException
{
	public QueueException(String name)
	{
		super(name);
	}

	public QueueException()
	{
		super();
	}
}
