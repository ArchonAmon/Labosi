#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <malloc.h>

int binarnoTrazi(float *polje, int n, float x) {
	int gg = n;
	int dg = 0;
	int srednja;
	do {
		srednja = (dg + gg) / 2;
		if (x == polje[srednja]) return srednja;
		else if (x > polje[srednja]) dg = srednja;
		else gg = srednja ;

	} while (dg <= gg);
	return -1;

}

int main(void) {
	float *p;
	int n = 5;
	int i = 0;
	int indeks;
	p = (float *)malloc(n * sizeof(float));
	do {
		p[i] = (i * 1.1);
		i++;
	} while (i < n);
	indeks = binarnoTrazi(&p[0], n, 3.3);
	printf("%d", indeks);
	scanf("%d", &indeks);


	return 0;
}