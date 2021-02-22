#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <malloc.h>


void Zamijeni(char *prvi, char *drugi) {
	char *pom;
	pom = (char *)malloc(sizeof(char) * 10);
	strcpy(pom, prvi);
	strcpy(prvi, drugi);
	strcpy(drugi, pom);
	free(pom);
}

void BubbleSortPoboljsani(char **A, int N, int smjer) {
	int i, j;
	int pom = N;
	for (i = 0; i < pom; i++) {
		for (j = 0; j < N - 1; j++) {
			if (strcmp(A[j], A[j + 1]) > 0) Zamijeni(A[j], A[j + 1]);
		}
		N--;
	}
}

int main(void) {
	char *polje[10];
	char *pom;
	int n = 0;
	char c;
	int i = 0;
	do {
		i = 0;
		pom = (char *)malloc(sizeof(char));
		do {
			c = getc(stdin);
			if (c != '\n') {
				pom[i] = c;
				i++;
				pom = (char *)realloc(pom, sizeof(char) * (i + 1));
			}

		} while (c != '\n');
		pom[i] = 0;
		polje[n] = pom;
		n++;
	} while (n < 2);
	BubbleSortPoboljsani(&polje[0], n, 0);
	for (i = 0; i < n; i++) {
		printf("%s ", polje[i]);
	}
	scanf("%d", &i);
	return 0;
}