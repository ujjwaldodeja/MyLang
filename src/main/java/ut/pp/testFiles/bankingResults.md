### Output:
```
Sprockell 0 says 80
Sprockell 0 says 250
Sprockell 0 says 270
```

### Output without locks:
```
Sprockell 0 says 80
Sprockell 0 says 170
Sprockell 0 says 270
```

### Spril instructions:
```
  0: Branch regSprID (Abs 131)                --Make all threads other than main jump to the idle loop
  1: WriteInstr reg0 (DirAddr 0)              --Declaration of account1
  2: WriteInstr reg0 (DirAddr 1)              --Declaration of account2
  3: WriteInstr reg0 (DirAddr 2)              --Declaration of account3
  4: Load (ImmValue 100) regA                 --Assignment to account1
  5: WriteInstr regA (DirAddr 0)              
  6: Load (ImmValue 200) regA                 --Assignment to account2
  7: WriteInstr regA (DirAddr 1)              
  8: Load (ImmValue 300) regA                 --Assignment to account3
  9: WriteInstr regA (DirAddr 2)              
 10: Load (ImmValue 69) regA                  --Fork to trans1
 11: WriteInstr regA (DirAddr 3)              
 12: Load (ImmValue 38) regA                  --Fork to trans2
 13: WriteInstr regA (DirAddr 6)              
 14: Load (ImmValue 100) regA                 --Fork to trans3
 15: WriteInstr regA (DirAddr 8)              
 16: ReadInstr (DirAddr 3)                    --Wait on join(trans1)
 17: Receive regA                             
 18: Compute GtE regA reg0 regA               
 19: Branch regA (Abs 16)                     
 20: ReadInstr (DirAddr 6)                    --Wait on join(trans2)
 21: Receive regA                             
 22: Compute GtE regA reg0 regA               
 23: Branch regA (Abs 20)                     
 24: ReadInstr (DirAddr 8)                    --Wait on join(trans3)
 25: Receive regA                             
 26: Compute GtE regA reg0 regA               
 27: Branch regA (Abs 24)                     
 28: ReadInstr (DirAddr 0)                    --(Loading shared var account1) // Print
 29: Receive regA                             
 30: WriteInstr regA numberIO                 
 31: ReadInstr (DirAddr 1)                    --(Loading shared var account2) // Print
 32: Receive regA                             
 33: WriteInstr regA numberIO                 
 34: ReadInstr (DirAddr 2)                    --(Loading shared var account3) // Print
 35: Receive regA                             
 36: WriteInstr regA numberIO                 
 37: Jump (Abs 151)                           
 38: TestAndSet (DirAddr 5)                   --Try to get lock(acc2) // Start of thread trans2
 39: Receive regA                             
 40: Compute Equal regA reg0 regA             
 41: Branch regA (Abs 38)                     
 42: TestAndSet (DirAddr 7)                   --Try to get lock(acc3)
 43: Receive regA                             
 44: Compute Equal regA reg0 regA             
 45: Branch regA (Abs 42)                     
 46: Load (ImmValue 80) regB                  --Check if condition
 47: ReadInstr (DirAddr 2)                    --(Loading shared var account3)
 48: Receive regA                             
 49: Compute GtE regA regB regA               
 50: Compute Equal regA reg0 regA             
 51: Branch regA (Abs 62)                     
 52: Load (ImmValue 80) regB                  --Assignment to account2 // If body
 53: ReadInstr (DirAddr 1)                    --(Loading shared var account2)
 54: Receive regA                             
 55: Compute Add regA regB regA               
 56: WriteInstr regA (DirAddr 1)              
 57: Load (ImmValue 80) regB                  --Assignment to account3
 58: ReadInstr (DirAddr 2)                    --(Loading shared var account3)
 59: Receive regA                             
 60: Compute Sub regA regB regA               
 61: WriteInstr regA (DirAddr 2)              
 62: Load (ImmValue 0) regA                   --Release lock(acc2)
 63: WriteInstr regA (DirAddr 5)              
 64: Load (ImmValue 0) regA                   --Release lock(acc3)
 65: WriteInstr regA (DirAddr 7)              
 66: Load (ImmValue (-1)) regA                --Set thread_done flag for join(trans2)
 67: WriteInstr regA (DirAddr 6)              
 68: Jump (Abs 151)                           
 69: TestAndSet (DirAddr 4)                   --Try to get lock(acc1) // Start of thread trans1
 70: Receive regA                             
 71: Compute Equal regA reg0 regA             
 72: Branch regA (Abs 69)                     
 73: TestAndSet (DirAddr 5)                   --Try to get lock(acc2)
 74: Receive regA                             
 75: Compute Equal regA reg0 regA             
 76: Branch regA (Abs 73)                     
 77: Load (ImmValue 30) regB                  --Check if condition
 78: ReadInstr (DirAddr 1)                    --(Loading shared var account2)
 79: Receive regA                             
 80: Compute GtE regA regB regA               
 81: Compute Equal regA reg0 regA             
 82: Branch regA (Abs 93)                     
 83: Load (ImmValue 30) regB                  --Assignment to account1 // If body
 84: ReadInstr (DirAddr 0)                    --(Loading shared var account1)
 85: Receive regA                             
 86: Compute Add regA regB regA               
 87: WriteInstr regA (DirAddr 0)              
 88: Load (ImmValue 30) regB                  --Assignment to account2
 89: ReadInstr (DirAddr 1)                    --(Loading shared var account2)
 90: Receive regA                             
 91: Compute Sub regA regB regA               
 92: WriteInstr regA (DirAddr 1)              
 93: Load (ImmValue 0) regA                   --Release lock(acc1)
 94: WriteInstr regA (DirAddr 4)              
 95: Load (ImmValue 0) regA                   --Release lock(acc2)
 96: WriteInstr regA (DirAddr 5)              
 97: Load (ImmValue (-1)) regA                --Set thread_done flag for join(trans1)
 98: WriteInstr regA (DirAddr 3)              
 99: Jump (Abs 151)                           
100: TestAndSet (DirAddr 4)                   --Try to get lock(acc1) // Start of thread trans3
101: Receive regA                             
102: Compute Equal regA reg0 regA             
103: Branch regA (Abs 100)                    
104: TestAndSet (DirAddr 7)                   --Try to get lock(acc3)
105: Receive regA                             
106: Compute Equal regA reg0 regA             
107: Branch regA (Abs 104)                    
108: Load (ImmValue 50) regB                  --Check if condition
109: ReadInstr (DirAddr 0)                    --(Loading shared var account1)
110: Receive regA                             
111: Compute GtE regA regB regA               
112: Compute Equal regA reg0 regA             
113: Branch regA (Abs 124)                    
114: Load (ImmValue 50) regB                  --Assignment to account3 // If body
115: ReadInstr (DirAddr 2)                    --(Loading shared var account3)
116: Receive regA                             
117: Compute Add regA regB regA               
118: WriteInstr regA (DirAddr 2)              
119: Load (ImmValue 50) regB                  --Assignment to account1
120: ReadInstr (DirAddr 0)                    --(Loading shared var account1)
121: Receive regA                             
122: Compute Sub regA regB regA               
123: WriteInstr regA (DirAddr 0)              
124: Load (ImmValue 0) regA                   --Release lock(acc1)
125: WriteInstr regA (DirAddr 4)              
126: Load (ImmValue 0) regA                   --Release lock(acc3)
127: WriteInstr regA (DirAddr 7)              
128: Load (ImmValue (-1)) regA                --Set thread_done flag for join(trans3)
129: WriteInstr regA (DirAddr 8)              
130: Jump (Abs 151)                           
131: Load (ImmValue 1) regA                   --Get thread memory location for thread 1
132: Compute NEq regA regSprID regA           
133: Branch regA (Rel 3)                      
134: Load (ImmValue 6) regA                   
135: Jump (Abs 146)                           
136: Load (ImmValue 2) regA                   --Get thread memory location for thread 2
137: Compute NEq regA regSprID regA           
138: Branch regA (Rel 3)                      
139: Load (ImmValue 3) regA                   
140: Jump (Abs 146)                           
141: Load (ImmValue 3) regA                   --Get thread memory location for thread 3
142: Compute NEq regA regSprID regA           
143: Branch regA (Rel 3)                      
144: Load (ImmValue 8) regA                   
145: Jump (Abs 146)                           
146: ReadInstr (IndAddr regA)                 --Thread pool idle loop
147: Receive regB                             
148: Compute Equal regB reg0 regC             
149: Branch regC (Rel (-3))                   
150: Jump (Ind regB)                          
151: EndProg                                  
152: Load (ImmValue 0) regA                   --Error Handling
153: Compute NEq regF regA regB               
154: Branch regB (Rel 51)                     
155: Load (ImmValue 69) regA                  --Print error DivByZero
156: WriteInstr regA charIO                   
157: Load (ImmValue 114) regA                 
158: WriteInstr regA charIO                   
159: Load (ImmValue 114) regA                 
160: WriteInstr regA charIO                   
161: Load (ImmValue 111) regA                 
162: WriteInstr regA charIO                   
163: Load (ImmValue 114) regA                 
164: WriteInstr regA charIO                   
165: Load (ImmValue 58) regA                  
166: WriteInstr regA charIO                   
167: Load (ImmValue 32) regA                  
168: WriteInstr regA charIO                   
169: Load (ImmValue 67) regA                  
170: WriteInstr regA charIO                   
171: Load (ImmValue 97) regA                  
172: WriteInstr regA charIO                   
173: Load (ImmValue 110) regA                 
174: WriteInstr regA charIO                   
175: Load (ImmValue 110) regA                 
176: WriteInstr regA charIO                   
177: Load (ImmValue 111) regA                 
178: WriteInstr regA charIO                   
179: Load (ImmValue 116) regA                 
180: WriteInstr regA charIO                   
181: Load (ImmValue 32) regA                  
182: WriteInstr regA charIO                   
183: Load (ImmValue 100) regA                 
184: WriteInstr regA charIO                   
185: Load (ImmValue 105) regA                 
186: WriteInstr regA charIO                   
187: Load (ImmValue 118) regA                 
188: WriteInstr regA charIO                   
189: Load (ImmValue 105) regA                 
190: WriteInstr regA charIO                   
191: Load (ImmValue 100) regA                 
192: WriteInstr regA charIO                   
193: Load (ImmValue 101) regA                 
194: WriteInstr regA charIO                   
195: Load (ImmValue 32) regA                  
196: WriteInstr regA charIO                   
197: Load (ImmValue 98) regA                  
198: WriteInstr regA charIO                   
199: Load (ImmValue 121) regA                 
200: WriteInstr regA charIO                   
201: Load (ImmValue 32) regA                  
202: WriteInstr regA charIO                   
203: Load (ImmValue 48) regA                  
204: WriteInstr regA charIO                   
205: Load (ImmValue 1) regA                   
206: Compute NEq regF regA regB               
207: Branch regB (Rel 113)                    
208: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
209: WriteInstr regA charIO                   
210: Load (ImmValue 114) regA                 
211: WriteInstr regA charIO                   
212: Load (ImmValue 114) regA                 
213: WriteInstr regA charIO                   
214: Load (ImmValue 111) regA                 
215: WriteInstr regA charIO                   
216: Load (ImmValue 114) regA                 
217: WriteInstr regA charIO                   
218: Load (ImmValue 58) regA                  
219: WriteInstr regA charIO                   
220: Load (ImmValue 32) regA                  
221: WriteInstr regA charIO                   
222: Load (ImmValue 65) regA                  
223: WriteInstr regA charIO                   
224: Load (ImmValue 116) regA                 
225: WriteInstr regA charIO                   
226: Load (ImmValue 116) regA                 
227: WriteInstr regA charIO                   
228: Load (ImmValue 101) regA                 
229: WriteInstr regA charIO                   
230: Load (ImmValue 109) regA                 
231: WriteInstr regA charIO                   
232: Load (ImmValue 112) regA                 
233: WriteInstr regA charIO                   
234: Load (ImmValue 116) regA                 
235: WriteInstr regA charIO                   
236: Load (ImmValue 105) regA                 
237: WriteInstr regA charIO                   
238: Load (ImmValue 110) regA                 
239: WriteInstr regA charIO                   
240: Load (ImmValue 103) regA                 
241: WriteInstr regA charIO                   
242: Load (ImmValue 32) regA                  
243: WriteInstr regA charIO                   
244: Load (ImmValue 116) regA                 
245: WriteInstr regA charIO                   
246: Load (ImmValue 111) regA                 
247: WriteInstr regA charIO                   
248: Load (ImmValue 32) regA                  
249: WriteInstr regA charIO                   
250: Load (ImmValue 97) regA                  
251: WriteInstr regA charIO                   
252: Load (ImmValue 99) regA                  
253: WriteInstr regA charIO                   
254: Load (ImmValue 99) regA                  
255: WriteInstr regA charIO                   
256: Load (ImmValue 101) regA                 
257: WriteInstr regA charIO                   
258: Load (ImmValue 115) regA                 
259: WriteInstr regA charIO                   
260: Load (ImmValue 115) regA                 
261: WriteInstr regA charIO                   
262: Load (ImmValue 32) regA                  
263: WriteInstr regA charIO                   
264: Load (ImmValue 111) regA                 
265: WriteInstr regA charIO                   
266: Load (ImmValue 117) regA                 
267: WriteInstr regA charIO                   
268: Load (ImmValue 116) regA                 
269: WriteInstr regA charIO                   
270: Load (ImmValue 32) regA                  
271: WriteInstr regA charIO                   
272: Load (ImmValue 111) regA                 
273: WriteInstr regA charIO                   
274: Load (ImmValue 102) regA                 
275: WriteInstr regA charIO                   
276: Load (ImmValue 32) regA                  
277: WriteInstr regA charIO                   
278: Load (ImmValue 98) regA                  
279: WriteInstr regA charIO                   
280: Load (ImmValue 111) regA                 
281: WriteInstr regA charIO                   
282: Load (ImmValue 117) regA                 
283: WriteInstr regA charIO                   
284: Load (ImmValue 110) regA                 
285: WriteInstr regA charIO                   
286: Load (ImmValue 100) regA                 
287: WriteInstr regA charIO                   
288: Load (ImmValue 115) regA                 
289: WriteInstr regA charIO                   
290: Load (ImmValue 32) regA                  
291: WriteInstr regA charIO                   
292: Load (ImmValue 105) regA                 
293: WriteInstr regA charIO                   
294: Load (ImmValue 110) regA                 
295: WriteInstr regA charIO                   
296: Load (ImmValue 100) regA                 
297: WriteInstr regA charIO                   
298: Load (ImmValue 101) regA                 
299: WriteInstr regA charIO                   
300: Load (ImmValue 120) regA                 
301: WriteInstr regA charIO                   
302: Load (ImmValue 32) regA                  
303: WriteInstr regA charIO                   
304: Load (ImmValue 111) regA                 
305: WriteInstr regA charIO                   
306: Load (ImmValue 102) regA                 
307: WriteInstr regA charIO                   
308: Load (ImmValue 32) regA                  
309: WriteInstr regA charIO                   
310: Load (ImmValue 97) regA                  
311: WriteInstr regA charIO                   
312: Load (ImmValue 114) regA                 
313: WriteInstr regA charIO                   
314: Load (ImmValue 114) regA                 
315: WriteInstr regA charIO                   
316: Load (ImmValue 97) regA                  
317: WriteInstr regA charIO                   
318: Load (ImmValue 121) regA                 
319: WriteInstr regA charIO                   
320: EndProg                      
``` 