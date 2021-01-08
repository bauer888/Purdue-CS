#include <stdio.h>
#include <stdlib.h>
#include "basemm.h"
//#include "mytimer.h"

void VerifyResult(int rows)
{
	int i, j;
	long val =0;
	for (i=0; i<rows; i++) 
		{
			val += C[i];
			C[i] = 0;
		}

	printf("%ld\n", val);
}
int basematvec(int n1, int n2, int* B)
{
   int i, j, k, i1, j1;
   //interchange loop nesting
  /*
  for (i=0; i<n1; i++)  C[i] = 0;
  for (j=0; j<n2; j++)
  	for (i=0; i<n1; i++)  
	     	C[i] += A[i][j] * V[j];
 */
   
 for (i=0; i<n1; i++) {
	  C[i] = 0;
  	for (j=0; j<n2; j++) {
		C[i] += B[i+n2*j] * V[j];
	}
}
   return 0;

}


int main(int argc, char **argv)
{
   int i,j, k;
   int Rows,Cols;
   int Repeats = 1;

   int* B = (int*) malloc(sizeof(int) * atoi(argv[1]) * atoi(argv[2]));
   /*A = (int**) malloc(sizeof(int*) * atoi(argv[2]));
   for (int x = 0; x < atoi(argv[2]); x++) {
	A[x] = (int*) malloc(sizeof(int) * atoi(argv[1])); 
   }*/
   V = (int*) malloc(sizeof(int) * atoi(argv[2]));
   C = (int*) malloc(sizeof(int) * atoi(argv[1]));

   
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
           B[i+Cols*j] = 1;

   for (i=0; i<Cols; i++) 
           V[i] = 1;

   for (i=0; i<Repeats; i++)
        basematvec(Rows,Cols,B);
 //  diff = TIMER_END();
 //  printf("execution time %f\n", diff);
   VerifyResult(Rows);

   free(B);
   free(V);
   free(C);
   return 0;
}
