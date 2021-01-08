
        cmp r5, r0
        addeq r5, r5, r0
	moveq r0, r5
	moveq r7, #1
	swieq 0

        cmp r5, r1
        addeq r5, r5, r1
       	moveq r0, r5
	moveq r7, #1
        swieq 0

        cmp r5, r2
        addeq r5, r5, r2
        moveq r0, r5
        moveq r7, #1
	swieq 0

        cmp r5, r3
        addeq r5, r5, r3
	moveq r0, r5
	moveq r7, #1
        swieq 0

        cmp r5, r4
        addeq r5, r5, r4
        moveq r0, r5
	moveq r7, #1
	swieq 0

        cmp r5, #5
        addeq r5, r5, r5
	moveq r0, r5
	moveq r7, #1
	swieq 0
