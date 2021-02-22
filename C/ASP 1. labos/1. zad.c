#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
/*ne zaboraviti header*/

void ispis(float *polje, int n) {
	if (n > 1) {
		ispis(&polje[0], n - 1);
	}
	if (polje[n-1]<0) {
		printf("%f ", polje[n-1]);
	}
	return;
}





int main(void) {
	float *p;
	int n, i = 0;

	scanf("%d", &n);

	p = (float *)malloc(n * sizeof(float));
	if (p == NULL) {
		printf("Nema dovoljno memorije za ucitati polje");
		exit(1);
	}
	do {
		scanf("%f", &p[i]);
			i++;
	} while (i < n);
	ispis(&p[0], n);
	free(p);
	scanf("%f", &p[i]);
	
	return 0;
}