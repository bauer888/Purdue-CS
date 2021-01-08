#ifndef MYTIMER_H
#define MYTIMER_H

#include <time.h>
#include <sys/time.h>

struct timeval _mytm, _mynewtime; 

#define TIMER_BEGIN()   gettimeofday(&_mytm, NULL)

 double TIMER_END()      
{  gettimeofday(&_mynewtime, NULL);
    return (_mynewtime.tv_sec-_mytm.tv_sec-1) + (1000000.0 + _mynewtime.tv_usec - _mytm.tv_usec)/1000000;    
 }  

#endif /* MYTIMER_H */
