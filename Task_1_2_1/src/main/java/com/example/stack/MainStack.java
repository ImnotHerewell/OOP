package com.example.stack;


public class MainStack <T> {
  int curSize = 0;
  static final int MAX_SIZE = 10000;
  public T[] array = (T[]) new Object[MAX_SIZE];
  public static void main(String[] args) {}

  boolean push(T inputElem) {
    if (curSize==MAX_SIZE) return false;
    this.array[curSize++]=inputElem;
    return true;
  }

  public T pop() {
    if (curSize == 0) return null;
    return this.array[--curSize];
  }
  public boolean pushStack (T[] inputArray)
  {
    if (inputArray.length+curSize>=MAX_SIZE)return false;
    for (T Elem:inputArray){
      this.push(Elem);
    }
    return true;
  }
  public MainStack<T> popStack(int quantityOfElems){
    MainStack <T> newStack = new MainStack<T>();
    if (quantityOfElems>curSize)return null;
    else {
      for (int indexArray = curSize-quantityOfElems;indexArray<curSize;indexArray++){
        newStack.push(this.array[indexArray]);
      }
      for (int quantityI=0;quantityI<quantityOfElems;quantityI++){
        this.pop();
      }
      return newStack;
    }
  }
  public int count (){
    return curSize;
  }
}
