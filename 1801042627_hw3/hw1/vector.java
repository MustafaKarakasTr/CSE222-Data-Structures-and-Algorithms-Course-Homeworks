package mustafa.karakas.hw1;

/**
 * container class
 */
public class vector<E> {
    private Object[] arr;
    private int used=0;
    private int capacity;
    /**
     * constructs the empty vector
     */
    public vector(){
        this(1);
    }
    /**
     * @return number of elements in the vector
     */
    public int getUsed(){return used;}
    /**
     * constructs the vector with the given size
     * @param size size of the vector
     */
    public vector(int size){
        used = 0;
        if(capacity<1) 
            capacity = 1;
        else
            capacity = size;
        arr = (E[])new Object[capacity];
        /*for(int i=0;i<capacity;i++){
            arr[i] = new E();
        }*/
    }
    /**
     * adds elements to the end of array
     * @param value element to be added
     */
    public void push_back(E value){
        if(arr.length == used){
            capacity *= 2;
            Object[] temp = (E[])new Object[capacity];
            for(int i=0;i<arr.length;i++){
                temp[i] = arr[i];
            }
            arr = temp;
        }
        arr[used++] = value;

    }
    /**
     * clears the array
     */
    public void clear(){
        arr = null;
        arr =(E[]) new Object[1];
        capacity = 1;
        used = 0;
    }
    //*
    public void at(int index,E value)throws IndexOutOfBoundsException{
        if(index<used){
            arr[index] = value;
        }
        else{
            throw new IndexOutOfBoundsException();
            //System.out.println("Invalid argument\n");
        }
    }
    /**
     * returns the elements at the given index
     * @param index index of element
     * @return the value at the index
     * @throws IndexOutOfBoundsException if the given index is not valid throws
     */
    public E at(int index) throws IndexOutOfBoundsException{
        if(index<used && index >=0){
            return (E)arr[index];
        }
        else{
            throw new IndexOutOfBoundsException();
        }
        
    }
    /**
     * deletes the last element
     */
    public void pop_back(){
        if(used > 0 ){
            used--;
            if(used <= (capacity/4)){
                capacity /= 2;
                Object[] temp = (E[])new Object[capacity];
                for(int i=0;i<used;i++){
                    temp[i] = arr[i];
                }
                arr = temp;
            }
        }
    }
    /**
     * shows the elements of the vector
     */
    public void show(){
        for(int i=0;i<used;i++){
            System.out.printf(arr[i]+"\n");
        }
        System.out.println();
    }
    /**
     * deletes the element in the given index
     * @param index index of element
     */
    public void remove(int index){
        if(index<used){
            for(int i=index;i<arr.length-1;i++){
                arr[i] = arr[i+1];
            }
            pop_back();
        }
        else{
            System.out.println("Invalid index");
        }
    }
    /**
     * removes the given element 
     * @param e element to be removed
     * @return the removed element
     */
    public E remove(E e){
        for(int i=0;i<used;i++){
            if(arr[i].equals(e)){
                E deletedItem = e;
                remove(i);
                return e;
            }
        }
        return null;
    }
    
    
    /**
     * cheks if the given element available
     * @param value element searched for
     * @return index of element if the element is not available than returns -1
     */
    public int isAvailable(E value){
        for(int i=0;i<used;i++){
            if(arr[i].equals(value)){
                return i;
            }
        }
        return -1;
    }
    
    
}