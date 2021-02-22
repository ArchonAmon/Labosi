#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <malloc.h>


void Zamijeni(char *prvi, char *drugi) {
	char *pom;
	pom = (char *) malloc(10*sizeof(char));
	strcpy(pom, prvi);
	strcpy(prvi, drugi);
	strcpy(drugi, pom);
	free(pom);
}

void SelectionSort(char **A, int N, int smjer) {
	int i, j;
	char *pom;
	for (i = 0; i < N; i++) {
		pom = A[i];
		for (j = i; j < N; j++) {
			if (strcmp(A[j], pom) < 0) {
				pom = A[j];
		
		}
			Zamijeni(A[i], A[j]);

		}

	}
}

int main(void) {
	char *polje[10];
	char *pom;
	int i, n = 0;
	char c;

	do {
		pom = (char *)malloc(sizeof(char));
		i = 0;
		do {
			c = fgetc(stdin);
			if (c != '\n' && c != EOF) {
				pom[i] = c;
				i++;
				pom = (char *)realloc(pom, sizeof(char)*(i+1));
			}
		} while (c != '\n' && c != EOF);
			pom[i] = 0;
			polje[n] = pom;
			n++;
	} while (n < 2);
	SelectionSort(&polje[0], n, 0);
	for (i = 0; i < n; i++) {
		printf("%s ", polje[i]);
	}
	scanf("%d", &i);
	return 0;
}


