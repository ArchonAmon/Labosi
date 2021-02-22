#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <malloc.h>

void insertionSort(char **A, int n, char smjer) {
	int i, j;
	char *pom;
	for (i = 1; i < n; i++) {
		pom = A[i];
		for (j = i; j >= 1; j--) {
			if (strcmp(A[j - 1], pom) <= 0) break;
			A[j] = A[j - 1];
		}
		A[j] = pom;
	}
}

int main(void) {
	int i = 0, j;
	char *polje[10];
	char *pom;
	int n = 0;
	do {
			pom = (char *)malloc(sizeof(char));
			i = 0;
			do {
			j = fgetc(stdin);
			if (j != '\n' && j != EOF) {
				pom[i] = j;
				i++;
				pom = (char *)realloc(pom, sizeof(char)*(i + 1));
				
			}
		} while (j != EOF && j != '\n');
		pom[i] = 0;

		polje[n] = pom;
		n++;
	} while (n < 2);
	insertionSort(&polje[0], n, 0);
	for (i = 0; i < n; i++) {
		printf("%s", polje[i]);
		printf("\n");
	}
	scanf("%d", &i);
	return 0;
}
