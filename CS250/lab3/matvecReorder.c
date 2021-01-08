#include <stdio.h>
#include <stdlib.h>
#include "basemm.h"
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
int basematvec(int rows, int cols, int chunk)
{
   int i, j, k;
   for (i=0; i<cols; i++) {
	   C[i] = 0;
   }
   //interchange loop nesting
for (int el = 0; el < cols / chunk; el++) {
	for (k = 0; k < cols / chunk; k++) {
 		for (i = k * chunk; i<(k+1)*chunk; i++) {
			 // C[i] = 0;
  			for (j=k*chunk; j<(k+1)*chunk; j++) {
				C[i] += A[i][j] * V[j];
			}
		}	
	}
}
  
 
   return 0;
}


int main(int argc, char **argv)
{
   int i,j, k;
   int Rows,Cols;
   int Repeats = 1;
   int chunk = atoi(argv[4]);

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
        basematvec(Rows,Cols, chunk);
 //  diff = TIMER_END();
 //  printf("execution time %f\n", diff);
   VerifyResult();
   return 0;
}
