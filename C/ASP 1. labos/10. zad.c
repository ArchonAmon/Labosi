#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <malloc.h>
#include <string.h>

double f(double z, int k) {

	if (k == 0) return z;
	return (-1)*z*z / ((2 * k + 1)*(2 * k))*f(z, k - 1);
	
}


int main(void) {
	double rez;
	rez = f(0.5, 2);
	printf("%lf", rez);
	scanf("%lf", &rez);
	return 0;
}
