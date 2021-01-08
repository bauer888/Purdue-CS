make: matvec.c matvecRow.c matvecDy1D.c matvecDyRow.c basemm.h basemmS.h
	gcc -o matvec matvec.c
	gcc -o matvecO -O matvec.c
	gcc -o matvecO2 -O2 matvec.c
	gcc -o matvecO3 -O3 matvec.c
	gcc -o matvecRow matvecRow.c
	gcc -o matvecRowO -O matvecRow.c
	gcc -o matvecRowO2 -O2 matvecRow.c
	gcc -o matvecRowO3 -O3 matvecRow.c
	gcc -o matvecDy1D matvecDy1D.c
	gcc -o matvecDy1DO -O matvecDy1D.c
	gcc -o matvecDy1DO2 -O2 matvecDy1D.c
	gcc -o matvecDy1DO3 -O3 matvecDy1D.c
	gcc -o matvecDyRow matvecDyRow.c
	gcc -o matvecDyRowO -O matvecDyRow.c
	gcc -o matvecDyRowO2 -O2 matvecDyRow.c
	gcc -o matvecDyRowO3 -O3 matvecDyRow.c
clean: 
	rm -f matvec*.o
