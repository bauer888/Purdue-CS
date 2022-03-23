import pyshark
# I looked up an easy way to iterate packet traces in Python and found 
# pyshark from this link: https://subscription.packtpub.com/book/cloud_and_networking/9781789958096/2/ch02lvl1sec08/interacting-with-wireshark-with-pyshark
# I downloaded it from this link: https://pypi.org/project/pyshark/
import sys

endPoints = {}

trace = pyshark.FileCapture(r"C:\Users\jnbau\Downloads\project2.pcap", display_filter='tcp.flags.syn == 1 and tcp.flags.ack == 0')

finalEnd = ""
mDiff = 0
for pkt in trace:
    seqList = []
    seqnum = int(pkt.tcp.seq)
    source = pkt.ip.src
    destination = pkt.ip.dst
    end = (source, destination)
    if end in endPoints:
        seqList = endPoints[end]
    seqList.append(seqnum)
    endPoints[end] = seqList
    max = 0
    min = sys.maxsize
    listLength = len(seqList)
    for i in range(listLength):
        if seqList[i] > max:
            max = seqList[i]
        if seqList[i] < min:
            min = seqList[i]
        diff = max - min
    if diff > mDiff:
        mDiff = diff
        finalEnd = end

print(finalEnd)
