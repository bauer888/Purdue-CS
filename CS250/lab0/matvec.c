#include <stdio.h>
#include <stdlib.h>
#include "basemm.h"
//#include "mytimer.h"

void VerifyResult()
{
	int i, j;
	long val =0;
	printf("i in verify: %p", &i);
	printf("j in verify: %p", &j);
	printf("val in verify: %p", &val);
	printf("verify in verify: %p", &VerifyResult);
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
   printf("i in base: %p", &i);
   printf("j in base: %p", &j);
   printf("k in base: %p", &k);
   printf("i1 in base: %p", &i1);
   printf("j1 in base: %p", &j1);
   printf("base in base: %p", &basematvec);
// interchange loop nesting
   /*
  for (i=0; i<n1; i++) {
      C[i] = 0;
      for (j=0; j<n2; j++)
	     	C[i] += A[i][j] * V[j];
  }
  */
  for (i=0; i<n1; i++)  C[i] = 0;
  for (j=0; j<n2; j++)
  	for (i=0; i<n1; i++)  
	     	C[i] += A[i][j] * V[j];
 
   return 0;

}


int main(int argc, char **argv)
{
   int i,j, k;
   int Rows,Cols;
   int Repeats = 1;

   int** A = (int**) malloc(sizeof(int*) * atoi(argv[2]));
   for (int x = 0; x < atoi(argv[2]); x++) {
	A[x] = (int*) malloc(sizeof(int) * atoi(argv[1])); 
   }
   int* V = (int*) malloc(sizeof(int) * atoi(argv[2]));
   int* C = (int*) malloc(sizeof(int) * atoi(argv[1]));

   printf("i in main: %p", &i);
   printf("j in main: %p", &j);
   printf("k in main: %p", &k);
   printf("Rows: %p", &Rows);
   printf("Cols: %p", &Cols);
   printf("Repeats: %p", &Repeats);
   printf("A[0][0]: %p", &A[0][0]);
   printf("A[3][2]: %p", &A[3][2]);
   printf("V[2]: %p", &V[2]);
   printf("C[3]: %p", &C[3]);

   if (argc<3) {
	   printf("command rowsize columnsize\n");
	   return(1);
   }

   Rows = atoi(argv[1]);
   Cols = atoi(argv[2]);

   if (argc==4) Repeats = atoi(argv[3]);

   double diff;
   printf("diff in main: %p", &diff);
   printf("main in main: %p", &main);
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
   for (int x = 0; x < atoi(argv[2]); x++) {
	free(A[x]);
   }
   free(A);
   free(V);
   free(C);
   return 0;
}
