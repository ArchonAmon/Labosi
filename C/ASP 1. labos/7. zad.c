#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <malloc.h>

int *kvadriranje(int *niz, int n) {
	int i, j = 0, *niz2;
	int pom;
	int c;
	niz2 = (int *)malloc(n * sizeof(int));

	for (i = 0; i <= n; i++) {
		niz2[i] = niz[i];
	}
	for (i = 0; i <= n*n*n; i++) {
		do {
			c = rand() % n;
		} while (c == n-1);
		pom = niz2[c];
		niz2[c] = niz2[c+1];
		niz2[c+1] = pom;

	}


	for (i = 0; i < n; i++) {
		niz2[i] *= niz2[i];
	}

	return &niz2[0];
}


int main(void) {
	int n, *p, *niz2;
	int brojac = 0;
	srand((unsigned)time(NULL));
	scanf("%d", &n);

	p = (int *)malloc(n * sizeof(int));

	do {
		scanf("%d", &p[brojac]);
		brojac++;
	} while (brojac < n);

		niz2 = kvadriranje(&p[0], n);

		for (brojac = 0; brojac < n; brojac++) {
			printf("%d ", niz2[brojac]);
}
		scanf("%d", &n);
	return 0;
}