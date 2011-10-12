; Kellen Donohue
; 10-02-10
; CSE 378 - HW1
; hw1-prog1.s (Non-optimized)
	.file	"hw1-prog1.c"
	.section	.rodata
.LC0:
	.string	"Sum: %d\n"		; The formatting string to be displayed at the end of the program
	.text
.globl main
	.type	main, @function	; set main as a global val, it's a function
main:
.LFB2:
	pushq	%rbp			; push the command line args onto the stack
.LCFI0:
	movq	%rsp, %rbp		; set the stack pointer to rbp
.LCFI1:
	pushq	%rbx            ; push rbx onto the stack, we'll pull it off later
.LCFI2:
	subq	$40, %rsp		; subtract 40 from the stack pointer, we'll reset it later
.LCFI3:						; main()
	movl	%edi, -28(%rbp) ; set argc as the source of a string op?
	movq	%rsi, -40(%rbp)	; set argv as the destination of a string op?
	cmpl	$3, -28(%rbp)	; compare argc to 3
	je	.L2 				; jump to L2 if the result is 0 (if argc == 3)
	movl	$0, -44(%rbp)	; initalize i to be 0
	jmp	.L4					; jump to L4 to finish the prog
.L2:						; line 8 of the program
	movq	-40(%rbp), %rax ; set the start of argv as rax
	addq	$8, %rax		; go 8 bits past the start of argv, beacuse we want argv[1]
	movq	(%rax), %rdi	; set the value of rax into a register used by atoi
	movl	$0, %eax		; Set 0 into rax
	call	atoi			; convert the first arg to an integer, it goes into rax??
	movl	%eax, %ebx		; store eax in ebx (this saves the result of the atoi call)
	movq	-40(%rbp), %rax	; set the start of argv as rax again
	addq	$16, %rax		; go 16 bits past the start of argv, because we want argv[2]
	movq	(%rax), %rdi	; set the value of rax into a register used by atoi
	movl	$0, %eax		; Set 0 into rax
	call	atoi			; convert the arg to an integer
	leal	(%rbx,%rax), %eax	; add?? rax and rbx, store the result in eax
	movl	%eax, -12(%rbp)	; save the result in i (i is -12%rbp)
	movl	-12(%rbp), %esi ; set the value of i as a source for a string op (printf)
	movl	$.LC0, %edi		; store the raw text we want in edi, this will be used by printf
	movl	$0, %eax		; set eax to 0, for the printf call
	call	printf			; print the result to the screen
	movl	-12(%rbp), %eax ; pull the result of i and store it on the heap (next two lines)
	movl	%eax, -44(%rbp) ; store the result in the heap
.L4:						
	movl	-44(%rbp), %eax ; get the result from the heap, where i should be stored
	addq	$40, %rsp		; add 40 the the stack pointer, to restore it to its original position
	popq	%rbx			; pop rbx off the stack, this restores the stack
	leave					; leave the subroutine
	ret						; return the value of rax?? (i)
	
; The rest of the file looks like debugging info
.LFE2:
	.size	main, .-main
	.section	.eh_frame,"a",@progbits
.Lframe1:
	.long	.LECIE1-.LSCIE1
.LSCIE1:
	.long	0x0
	.byte	0x1
	.string	""
	.uleb128 0x1
	.sleb128 -8
	.byte	0x10
	.byte	0xc
	.uleb128 0x7
	.uleb128 0x8
	.byte	0x90
	.uleb128 0x1
	.align 8
.LECIE1:
.LSFDE1:
	.long	.LEFDE1-.LASFDE1
.LASFDE1:
	.long	.LASFDE1-.Lframe1
	.quad	.LFB2
	.quad	.LFE2-.LFB2
	.byte	0x4
	.long	.LCFI0-.LFB2
	.byte	0xe
	.uleb128 0x10
	.byte	0x86
	.uleb128 0x2
	.byte	0x4
	.long	.LCFI1-.LCFI0
	.byte	0xd
	.uleb128 0x6
	.byte	0x4
	.long	.LCFI3-.LCFI1
	.byte	0x83
	.uleb128 0x3
	.align 8
.LEFDE1:
	.ident	"GCC: (GNU) 4.0.3 (Ubuntu 4.0.3-1ubuntu5)" ; Info about the compiler
	.section	.note.GNU-stack,"",@progbits
