#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <malloc.h>
#include <string.h>

char *fja(char *niz, int n) {
	char *niz2;
	int i, j = 0;
	niz2 = (char *)malloc(n * sizeof(char)+1);
	for (i = 0; niz[i] != 0; i++) {

		niz2[j] = niz[i];



		if (niz[i] == ' ') {
			while (niz[i + 1] == ' ') {
				i++;
			}
		}
		j++;
	}
	j -= 1;
	(char *)realloc(niz2, j);
	
	niz2[j + 1] = '\0';
	
	return &niz2[0];
}


int main(void) {
	char polje[] = "sunce   nam     dolazi";
	char *pointer;
	int i, n = 0;
	
	for (i = 0; polje[i] != 0; i++) {
		n++;
	}

	pointer = fja(&polje[0], n);

		for (i = 0; pointer[i] != 0; i++) {
			printf("%c", pointer[i]);

	}
		scanf("%d", &n);
	return 0;
}