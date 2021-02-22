#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define max 10

typedef struct {
	int polje[max];
	int vrh;
} stog;

void inic(stog *stog) {
	stog->vrh = -1;
}


int dodaj(stog *stog, int broj) {
	if (stog->vrh > max) return 0;
	stog->vrh++;
	stog->polje[stog->vrh] = broj;
	return 1;
}

int skini (int *element, stog *stog) {
	if (stog->vrh < 0) return 0; 
	*element = stog->polje[stog->vrh];
	stog->vrh--;
	return 1;
}


int main(void) {
	stog stog;;
	int broj;
	int brojac = 0;

	inic(&stog);

	srand((unsigned)time(NULL));

	do {
		broj = rand() % 10 + 1;
		dodaj(&stog, broj);
		brojac++;
	} while (brojac < 10);

	for (brojac = 0; brojac < max; brojac++) {
		printf("%d ", stog.polje[brojac]);
	}


	brojac = 0;
	
	do {
		skini(&broj, &stog);
		brojac++;
	} while (brojac < 10);


	scanf("%d", &brojac);
	return 0;
}