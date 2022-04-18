//
//  main.c
//  C in one tutorial
//
//  Created by Zulkarnine on 12/9/14.
//  Copyright (c) 2014 Zulkarnine Mahmud. All rights reserved.
//

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#define NUMBER 100


const float pi=3.1416;

int globalint=10;

void addFunction(int firstNumber, int secondNumber){
    int result;
    result=firstNumber+secondNumber;
    printf("added successfully\n");
}


void swap(int* aa, int* bb){
    int temp=(*aa);
    (*aa)=(*bb);
    *bb=temp;
    
    printf("A: %d B: %d (inside the function)\n",*aa,*bb);
}


//typeName * name;
//int * name;
//char * name2;
//
//*name2=1286351875;

typedef int integerNumber;


typedef struct person {
    char name[50];
    int age;
    int weight;
    int height;
}Person;



void fileIOTest(){
    FILE* inputFile;
    
    inputFile=fopen("/Users/rezan_mahmud/Desktop/inpFile.txt", "r");
    
    if (inputFile!=NULL) {
        FILE* outFile=fopen("/Users/rezan_mahmud/Desktop/outFile.txt", "w");
        
        if (outFile!=NULL) {
            printf("file created successfully\n");
            
            //data read write
            
            Person samplePerson;
            fscanf(inputFile, "%s",samplePerson.name);
            fscanf(inputFile, "%d %d %d",&(samplePerson.age),&(samplePerson.weight),&(samplePerson.height));
    
            
            fprintf(outFile, "Name: %s\nAge: %d\nWeight: %d\nHeight: %d\n\n",samplePerson.name,samplePerson.age,samplePerson.weight,samplePerson.height);
            
            fclose(outFile);
        }else{
            printf("file could not be created\n");
        }
        
        fclose(inputFile);

    }
    
    
    
    
}



int main(int argc, const char * argv[]) {
    
    integerNumber a,b;
    float c,d;
    
    int * pointerX, pointerY;
    
    int userNum;
    int randomNumber=12443;
    
    int user5num[5];
    
    Person demoperson;
    
    fileIOTest();
    
//    printf("Your name please: ");
//    scanf("%s",demoperson.name);
//    printf("Your age please: ");
//    scanf("%d",&(demoperson.age));
//    printf("Your height please: ");
//    scanf("%d",&(demoperson.height));
//    printf("Your weight please: ");
//    scanf("%d",&(demoperson.weight));
//    
//    
//    printf("\n\nYour name is: %s\nYou are %d years old\nYour height is %dcm and you weigh %dkg\n",demoperson.name,demoperson.age,demoperson.height,demoperson.weight);
//    printf("\n\n");
//    
//    for (int i=-0; i<5; i++) {
//        printf("Element[%d] : %d\n",i,user5num[i]);
//    }
    
//    printf("please give a number to multiply by %d :",randomNumber);
//    scanf("%d",&userNum);
//    printf("%d x %d = %d\n",userNum,randomNumber,(userNum*randomNumber));
    return 0;
}
