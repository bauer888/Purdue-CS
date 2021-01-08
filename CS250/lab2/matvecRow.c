#include <stdio.h>
#include <stdlib.h>
#include "basemmS.h"
//#include "mytimer.h"

void VerifyResult()
{
	int i, j;
	long val =0;
	for (i=0; i<MAXROW; i++) 
		{
			val += C[i];
			C[i] = 0;
		}

	printf("%ld\n", val);
}
int basematvec(int n1, int n2)
{
   int i, j, k, i1, j1;
   //interchange loop nesting
 for (i=0; i<n1; i++) {
	  C[i] = 0;
  	for (j=0; j<n2; j++) {
		C[i] += A[i][j] * V[j];
	}
}	
  
 
   return 0;
}


int main(int argc, char **argv)
{
   int i,j, k;
   int Rows,Cols;
   int Repeats = 1;

   if (argc<3) {
	   printf("command rowsize columnsize\n");
	   return(1);
   }

   Rows = atoi(argv[1]);
   Cols = atoi(argv[2]);

   if (argc==4) Repeats = atoi(argv[3]);

   double diff;
   //   TIMER_BEGIN();
   // initialize matrix A and vector V
// interchange loop nesting
      for (j=0; j<Cols; j++) 
   for (i=0; i<Rows; i++) 
           A[i][j] = 1;

   for (i=0; i<Cols; i++) 
           V[i] = 1;

   for (i=0; i<Repeats; i++)
        basematvec(Rows,Cols);
 //  diff = TIMER_END();
 //  printf("execution time %f\n", diff);
   VerifyResult();
   return 0;
}
