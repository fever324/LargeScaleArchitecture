##1. Sharded System  
server i handles hash(key)%N = i   
get - 1ms   put - 2ms  
1 billion instructions/s  
p - percent that is put  

a). Max throughput n for a single node system
n * 0.1 * 2e-3 + n * 0.9 * 1e-3 = 1
n = 909 requests / s  


b). Max throughput n for 10 node with 10% put
n * 10 = 9090 requests/s

c). 
	n * 0.2 * 2e-3 + n * 0.8 * 1e-3 = 1
	n = 833 requests/s  

d). (n * p * 2e-3 + n * (1-p) * 1e-3)/N = 1  



##2. Replicated System
If it receives a put(k,v) request from a client it performs the
operation locally and also forwards the operation to all of the (N-1) other nodes, and does not return to the client until all N operations are completed. Clients are are assumed to send every request to a server node chosen uniformly at random.


***put time stay the same, get time is inversely proportional to N***  

a). Max throughput N = 1, p = 0.1  
`n * 0.1 * 2e-3 + n * 0.9 * 1e-3 = 1`  
n = 909 requests /s    

b). Max throughput N = 10. p = 0.1   
`n * p * 2e-3 + n * (1-p) * 1e-3 / N = 1  `


c). max of n is 5000 where the second term goes to 0 when N goes to infinity.


##3. Hybrid System.  
**Sharding for scalability, replicated system for resiliency.**    

Still using sharding but every server is backed up with one or more replicated system.   
Break up into N clusters, each clusters has M servers.  
Each cluster i is designated to handle `hash(key)%N = i`.  
put,get are uniformly randomly routed to one of the M servers inside the cluster.  

b).
throughput n , p = 0.1

`n * p * 2e-3 + n * (1-p) * 1e-3 / (N*M) = 1`









