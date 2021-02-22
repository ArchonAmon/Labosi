#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int zbrojiKvadrate(int *polje, int n) {
	int zbroj = 0;
	int i;
	if (n > 1) {
		zbroj += zbrojiKvadrate(&polje[0], n - 1);
	}
	for (i = 0; i < polje[n-1]; i++) {
		if ((i*i)==polje[n-1]) {
			zbroj += polje[n - 1];
		}

	}
	
return zbroj;
}




int main(void) {
	int zbroj, n=0, *p, i=0, j=0;
	scanf("%d", &n);
	p = (int *)malloc(n * sizeof(int));

	srand((unsigned)time(NULL));

	if (p == NULL) {
		printf("nedovoljno memorije");
		exit(1);
	}

	do {
		p[i] = rand() % 100 + 1;
		i++;
	} while (i < n);

	zbroj = zbrojiKvadrate(&p[0], n);
	printf("%d", zbroj);
	for (i = 0; i < n; i++) {
		printf("\n%d ", p[i]);
	}
	scanf("%d", &n);
	free(p);
	return 0;
}