#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <malloc.h>

double expo(double x, int n, int *fakt, double *xpot) {
	int i = 0, prethodnifakt = 1;
	if (n == 1) return 1;
	if (n == 2) return x + 1;
	for (i = 0; i < n; i++) {
		i *= i;
	}
	*xpot = (*xpot)*x;
	*fakt = (*fakt)*(i + 1);
	return *xpot / (*fakt) + expo(x, n - 1, fakt, xpot);
}

int main(void) {
	int n, fakt, i;
	double xpot;
	float x, *p;
	printf("Unesite broj clanova > \n");
	scanf("%d", &n);
	printf("\nUnesite potenciju > \n");
	scanf("%f", &x);
	p =(float *) malloc(n * sizeof(float));
	printf("\nPolje:\n [");
	for (i = 0; i<n; i++) {
		xpot = x;
		fakt = 1;
		*(p + i) = expo(x, i + 1, &fakt, &xpot);
		printf("%f ", *(p + i));
	}
	printf("]");
	scanf("%d", &n);
	return 0;
}


