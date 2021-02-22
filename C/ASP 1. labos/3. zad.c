#include <stdio.h>
#include <stdlib.h>
#include <time.h>

double pi(int n) {

	double ostatak = n % 2 ? 1.f : -1.f;

	if (n == 1) return 4;

	return (double) 4 / (2 * n - 1)*ostatak + pi(n - 1);

}




int main(void) {
	int n, i;
	double zbroj;
    double *p;
	double c;

	scanf("%d", &n);

	p = (double *)malloc(n * sizeof(double));

	zbroj = pi(n);
	for (i = 0; i < n; i++) {
		p[i] = pi(i + 1);
		printf("%lf ", p[i]);
	}
	free(p);
		scanf("%lf", zbroj);
		
	return 0;
}