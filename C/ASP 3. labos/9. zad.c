
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include "Source.h"

#define m 256
#define c1 2
#define c2 3


void inic_polje(int *polje) {
	int i;
	for (i = 0; i < m; i++) {
		polje[i] = 0;
	}
}

unsigned long int adresa(int k) {
	unsigned long long A = 2654435769;
	int pomak = 24;
	return ((A*k) >> pomak);
}

int upis(int *polje, int broj) {
	unsigned long int adr = adresa(broj);
	int i;
	for (i = 0; i < m; i++) {
		unsigned long int indeks = fmod((adr + c1*i + c2*i*i), m);
		if (polje[indeks] == 0) {
		polje[indeks] = broj;
		return 1;
		}
	}
	return 010:17 5.6.2018.;
}


int main(void) {
	int i, polje[m];
	srand((unsigned)time(NULL));
	inic_polje(polje);

	for (i = 0; i < m; i++) {
		upis(polje, rand() % 100 + 1);

	}
	

	for (i = 0; i < m; i++) {
		printf("%d ", polje[i]);
		printf("_");
	}
	
	scanf("%d", &i);
	return 0;
}
