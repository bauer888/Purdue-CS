import pyshark
# I looked up an easy way to iterate packet traces in Python and found 
# pyshark from this link: https://subscription.packtpub.com/book/cloud_and_networking/9781789958096/2/ch02lvl1sec08/interacting-with-wireshark-with-pyshark
# I downloaded it from this link: https://pypi.org/project/pyshark/

ips = {}

trace = pyshark.FileCapture(r"C:\Users\jnbau\Downloads\project2.pcap", display_filter='udp and not dns and not mdns and not browser')

finalEnd = ""
mTime = 0
for pkt in trace:
    timeToList = [] # <- get it? Time to list? :) I thought it was funny
    timeToLive = int(pkt.ip.ttl)
    source = pkt.ip.src
    destination = pkt.ip.dst
    end = (source, destination)
    if end in ips:
        timeToList = ips[end]
    timeToList.append(timeToLive)
    ips[end] = timeToList
    length = len(ips[end])
    if length > mTime:
        mTime = length
        finalEnd = end

print(finalEnd)