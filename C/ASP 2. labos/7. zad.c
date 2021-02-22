#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct st {
	char niz[40 + 1];
	struct st *sljed;
};
typedef struct st cvor;


int upis(cvor **glava, char *niz) {
	cvor *pom;
	int i = 0;
	pom = (cvor *)malloc(sizeof(cvor)+1);
	do {
		pom->niz[i] = niz[i];
		i++;
	} while (niz[i] != 0);
	pom->niz[i] = 0;
	pom->sljed = *glava;
	*glava = pom;
	return 1;
}

int main(void) {
	cvor *glava = NULL;
	int i;
	char niz[] = { "ADA" };
	char niz2[] = { "DAVA" };
	upis(&glava, niz2);
	upis(&glava, niz);
	while (glava) {
		for (i = 0; glava->niz[i] != 0; i++) {
			printf("%c", glava->niz[i]);
		}
		printf(" ");
		glava = glava->sljed;
	}
	scanf("%s", glava->niz);
	return 0;
}
