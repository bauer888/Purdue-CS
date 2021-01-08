#ifndef BASEMM_H
#define BASEMM_H

#define MAXROW 14096
#define MAXCOL 14096
#define clz 1 // cache line size

int A[MAXROW][MAXCOL];
int V[MAXCOL];
int C[MAXROW];

extern int basematrixmultiply(int n1, int n2, int n3);
extern void ClearResult();
#endif
