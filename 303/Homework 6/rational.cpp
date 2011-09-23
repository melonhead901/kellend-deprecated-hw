/*
* Implementation of rational number type
* CSE 303 HW6, Sp08
*/

#include "rational.h"


// Helper Function declarations

// Reduce the fraction with given numerator and denominator
void reduce(int &num, int &denom);

// Return the greatest common denominator of a and b
int gcd(int a, int b);

// Constructors

Rational::Rational()
{
  this->num = 0;
  this->denom = 1;
}

Rational::Rational(int n)
{
  this->num = n;
  this->denom = 1;
}

Rational::Rational(int n, int d)
{
  this->num = n;
  this->denom = d;
  reduce(this->num, this->denom);
}

// Accessors

int Rational::d()
{
  return this->denom;
}

int Rational::n()
{
  return this->num;
}

// Public Methods

Rational Rational::minus(Rational other)
{
  return Rational(this->n() * other.d() - other.n() * this->d(), this->d()*other.d());
}

Rational Rational::div(Rational other)
{
  return Rational(this->n() * other.d(), this->d() * other.n());
}

Rational Rational::times(Rational other)
{
  return Rational(this->n() * other.n(), this->d() * other.d());
}

Rational Rational::plus (Rational other)
{
  return Rational(this->n() * other.d() + other.n() * this->d(), this->d()*other.d());
}

// Helper functions

int gcd(int a, int b)
{
  // Case if one number is 0
  if(a==0 || b == 0)
  {
    return a+b; // GCD(0,a) = a | a != 0 && GCD(0,0) == 0
  }

  int temp;
  // Euclidean algorithim
  while(b!=0)
  {
    temp = a;
    a = b;
    b = temp % b;
  }
  return a;  
}

void reduce(int &a, int &b)
{
  int x = gcd(a, b);
  a /= x;
  b /= x;
}


