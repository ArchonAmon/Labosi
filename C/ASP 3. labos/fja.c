#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>

#define maxred 10
typedef struct {
	double polje[maxred];
	int ulaz, izlaz;
} Red;

void init_red(Red *red) {
	red->ulaz = 0;
	red->izlaz = 0;;
}

int dodajURed(double broj, Red *red) {
	if ((red->ulaz + 1) % maxred == red->izlaz) return 0;		//kada bismo povecali ulaz za 1 i modali ga dobijemo li broj veci od izlaza ne mozemo ga staviti u red
	red->ulaz++;		//jedan broj ulazi pa ulaz moramo povecati
	red->ulaz %= maxred;     //za slucaj da je broj koji je usao prekoracio 10. clan polja, moramo % ulaz
	red->polje[red->ulaz] = broj;	//red->ulaz je novi clan pa ga stavimo kao polje[ulaz]
	return 1;
}


int skiniIzReda(double *broj, Red *red) {			
	if (red->izlaz + 1 == red->ulaz) return 0;		//kako smo pri skidanju s reda prije povecavali izlaz, samo moramo provjeriti je li jednak ulazu, tj je li red prazan
	red->izlaz++;									//iste provjere kao i 
	red->izlaz %= maxred;							//za ulaz
	*broj = red->polje[red->izlaz];					//skidanje
	return 1;
}


int main(void) {
	Red red;
	int brojac = 0;
	double broj;							//ovaj broj nema vrijednost, a dobije ju samo kada skidamo element s reda
	double polje[] = { 1,2,3,4,5 };
	init_red(&red);
	do {
		dodajURed(polje[brojac], &red);
		brojac++;
	} while (brojac != 5);
	brojac = 0;
	do {
		skiniIzReda(&broj, &red);
		printf("Broj: %.2lf je skinut s reda\n", broj);
		brojac++;
	} while (brojac != 5);

	scanf("%d", &brojac);
	return 0;
}