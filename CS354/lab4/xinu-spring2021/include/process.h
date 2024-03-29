/* process.h - isbadpid */

/* Maximum number of processes in the system */

#ifndef NPROC
#define	NPROC		8
#endif		

#define XINUTEST  1 /* set to 1 */
#define XINUDEBUG 0 /* User debug. should be 0 in final turnin */

/* for print required for labs */
#if XINUTEST
#define XTEST_KPRINTF(...) kprintf(__VA_ARGS__)
#else
#define XTEST_KPRINTF(...)
#endif

/* User debug */
#if XINUDEBUG
#define XDEBUG_KPRINTF(...) kprintf(__VA_ARGS__)
#else
#define XDEBUG_KPRINTF(...)
#endif

/* Process state constants */

#define	PR_FREE		0	/* Process table entry is unused	*/
#define	PR_CURR		1	/* Process is currently running		*/
#define	PR_READY	2	/* Process is on ready queue		*/
#define	PR_RECV		3	/* Process waiting for message		*/
#define	PR_SLEEP	4	/* Process is sleeping			*/
#define	PR_SUSP		5	/* Process is suspended			*/
#define	PR_WAIT		6	/* Process is on semaphore queue	*/
#define	PR_RECTIM	7	/* Process is receiving with timeout	*/
#define PR_SENDERBLK	12	/* Process is blocked from receiving/sending messages */

/* Miscellaneous process definitions */

#define	PNMLEN		16	/* Length of process "name"		*/
#define	NULLPROC	0	/* ID of the null process		*/

/* Process initialization constants */

#define	INITSTK		65536	/* Initial process stack size		*/
#define	INITPRIO	20	/* Initial process priority		*/
#define	INITRET		userret	/* Address to which process returns	*/

/* Inline code to check process ID (assumes interrupts are disabled)	*/

#define	isbadpid(x)	( ((pid32)(x) < 0) || \
			  ((pid32)(x) >= NPROC) || \
			  (proctab[(x)].prstate == PR_FREE))

/* Number of device descriptors a process can have open */

#define NDESC		5	/* must be odd to make procent 4N bytes	*/

/* lab4 part 3 */
#define RECVQSIZE	5

/* lab4 part 4 */
#define NUMSENDERBLK	3

/* Definition of the process table (multiple of 32 bits) */

struct procent {		/* Entry in the process table		*/
	uint16	prstate;	/* Process state: PR_CURR, etc.		*/
	pri16	prprio;		/* Process priority			*/
	char	*prstkptr;	/* Saved stack pointer			*/
	char	*prstkbase;	/* Base of run time stack		*/
	uint32	prstklen;	/* Stack length in bytes		*/
	char	prname[PNMLEN];	/* Process name				*/
	sid32	prsem;		/* Semaphore on which process waits	*/
	pid32	prparent;	/* ID of the creating process		*/
	umsg32	prmsg;		/* Message sent to this process		*/
	bool8	prhasmsg;	/* Nonzero iff msg is valid		*/
	int16	prdesc[NDESC];	/* Device descriptors for process	*/
	umsg32	prrecvqueue[RECVQSIZE];	/* lab 4 part 3 */
	uint16	prqrecvbeg;
	uint16	prqrecvend;
	pid32	prsenderpid[RECVQSIZE];
	pid32	prsenderblkid[NUMSENDERBLK];
	uint16	prsndblkbeg;
	uint16	prsndblkend;
	uint16	prnumblockedsenders;
	umsg32	prsendermsg;
};

/* Marker for the top of a process stack (used to help detect overflow)	*/
#define	STACKMAGIC	0x0A0AAAA9

extern	struct	procent proctab[];
extern	int32	prcount;	/* Currently active processes		*/
extern	pid32	currpid;	/* Currently executing process		*/
extern	pid32	senderpid;
