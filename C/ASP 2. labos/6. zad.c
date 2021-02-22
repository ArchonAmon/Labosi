#include <stdlib.h>
#include <string.h>

struct st {
 int broj;
 struct st *sljed;
};
typedef struct st cvor;


int upis (cvor **glava, int element) {
 cvor *pom;
 pom = (cvor *) malloc (sizeof(cvor));
 for (; (*glava) && (*glava)->broj < element; glava = &((*glava)->sljed));
 pom->broj = element;
 pom->sljed = *glava;
 *glava = pom;
}

int main (void) {
 cvor *glava = NULL;
upis (&glava, 5);
upis (&glava, 3);
upis (&glava, 2);
upis (&glava, 7);
while (glava) {
 printf ("%d ", glava->broj);
	glava = glava->sljed;
}
return 0;
}
