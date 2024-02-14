### Output:
```
Sprockell 0 says 0
```

### Spril instructions:
```
  0: Branch regSprID (Abs 63)                 --Make all threads other than main jump to the idle loop
  1: WriteInstr reg0 (DirAddr 0)              --Declaration of a
  2: Load (ImmValue 0) regA                   --Assignment to a
  3: WriteInstr regA (DirAddr 0)              
  4: Load (ImmValue 37) regA                  --Fork to t2
  5: WriteInstr regA (DirAddr 1)              
  6: Load (ImmValue 0) regA                   --Assignment to b
  7: Store regA (DirAddr 0)                   
  8: Load (ImmValue 10) regB                  --While condition check
  9: Load (DirAddr 0) regA                    --(Loading var b)
 10: Compute Lt regA regB regA                
 11: Compute Equal regA reg0 regA             
 12: Branch regA (Abs 29)                     
 13: TestAndSet (DirAddr 2)                   --Try to get lock(l1) // While body
 14: Receive regA                             
 15: Compute Equal regA reg0 regA             
 16: Branch regA (Abs 13)                     
 17: Load (ImmValue 1) regB                   --Assignment to a
 18: ReadInstr (DirAddr 0)                    --(Loading shared var a)
 19: Receive regA                             
 20: Compute Sub regA regB regA               
 21: WriteInstr regA (DirAddr 0)              
 22: Load (ImmValue 0) regA                   --Release lock(l1)
 23: WriteInstr regA (DirAddr 2)              
 24: Load (ImmValue 1) regB                   --Assignment to b
 25: Load (DirAddr 0) regA                    --(Loading var b)
 26: Compute Add regA regB regA               
 27: Store regA (DirAddr 0)                   
 28: Jump (Abs 8)                             
 29: ReadInstr (DirAddr 1)                    --Wait on join(t2)
 30: Receive regA                             
 31: Compute GtE regA reg0 regA               
 32: Branch regA (Abs 29)                     
 33: ReadInstr (DirAddr 0)                    --(Loading shared var a) // Print
 34: Receive regA                             
 35: WriteInstr regA numberIO                 
 36: Jump (Abs 73)                            
 37: Load (ImmValue 0) regA                   --Assignment to b // Start of thread t2
 38: Store regA (DirAddr 0)                   
 39: Load (ImmValue 10) regB                  --While condition check
 40: Load (DirAddr 0) regA                    --(Loading var b)
 41: Compute Lt regA regB regA                
 42: Compute Equal regA reg0 regA             
 43: Branch regA (Abs 60)                     
 44: TestAndSet (DirAddr 2)                   --Try to get lock(l1) // While body
 45: Receive regA                             
 46: Compute Equal regA reg0 regA             
 47: Branch regA (Abs 44)                     
 48: Load (ImmValue 1) regB                   --Assignment to a
 49: ReadInstr (DirAddr 0)                    --(Loading shared var a)
 50: Receive regA                             
 51: Compute Add regA regB regA               
 52: WriteInstr regA (DirAddr 0)              
 53: Load (ImmValue 0) regA                   --Release lock(l1)
 54: WriteInstr regA (DirAddr 2)              
 55: Load (ImmValue 1) regB                   --Assignment to b
 56: Load (DirAddr 0) regA                    --(Loading var b)
 57: Compute Add regA regB regA               
 58: Store regA (DirAddr 0)                   
 59: Jump (Abs 39)                            
 60: Load (ImmValue (-1)) regA                --Set thread_done flag for join(t2)
 61: WriteInstr regA (DirAddr 1)              
 62: Jump (Abs 73)                            
 63: Load (ImmValue 1) regA                   --Get thread memory location for thread 1
 64: Compute NEq regA regSprID regA           
 65: Branch regA (Rel 3)                      
 66: Load (ImmValue 1) regA                   
 67: Jump (Abs 68)                            
 68: ReadInstr (IndAddr regA)                 --Thread pool idle loop
 69: Receive regB                             
 70: Compute Equal regB reg0 regC             
 71: Branch regC (Rel (-3))                   
 72: Jump (Ind regB)                          
 73: EndProg                                  
 74: Load (ImmValue 0) regA                   --Error Handling
 75: Compute NEq regF regA regB               
 76: Branch regB (Rel 51)                     
 77: Load (ImmValue 69) regA                  --Print error DivByZero
 78: WriteInstr regA charIO                   
 79: Load (ImmValue 114) regA                 
 80: WriteInstr regA charIO                   
 81: Load (ImmValue 114) regA                 
 82: WriteInstr regA charIO                   
 83: Load (ImmValue 111) regA                 
 84: WriteInstr regA charIO                   
 85: Load (ImmValue 114) regA                 
 86: WriteInstr regA charIO                   
 87: Load (ImmValue 58) regA                  
 88: WriteInstr regA charIO                   
 89: Load (ImmValue 32) regA                  
 90: WriteInstr regA charIO                   
 91: Load (ImmValue 67) regA                  
 92: WriteInstr regA charIO                   
 93: Load (ImmValue 97) regA                  
 94: WriteInstr regA charIO                   
 95: Load (ImmValue 110) regA                 
 96: WriteInstr regA charIO                   
 97: Load (ImmValue 110) regA                 
 98: WriteInstr regA charIO                   
 99: Load (ImmValue 111) regA                 
100: WriteInstr regA charIO                   
101: Load (ImmValue 116) regA                 
102: WriteInstr regA charIO                   
103: Load (ImmValue 32) regA                  
104: WriteInstr regA charIO                   
105: Load (ImmValue 100) regA                 
106: WriteInstr regA charIO                   
107: Load (ImmValue 105) regA                 
108: WriteInstr regA charIO                   
109: Load (ImmValue 118) regA                 
110: WriteInstr regA charIO                   
111: Load (ImmValue 105) regA                 
112: WriteInstr regA charIO                   
113: Load (ImmValue 100) regA                 
114: WriteInstr regA charIO                   
115: Load (ImmValue 101) regA                 
116: WriteInstr regA charIO                   
117: Load (ImmValue 32) regA                  
118: WriteInstr regA charIO                   
119: Load (ImmValue 98) regA                  
120: WriteInstr regA charIO                   
121: Load (ImmValue 121) regA                 
122: WriteInstr regA charIO                   
123: Load (ImmValue 32) regA                  
124: WriteInstr regA charIO                   
125: Load (ImmValue 48) regA                  
126: WriteInstr regA charIO                   
127: Load (ImmValue 1) regA                   
128: Compute NEq regF regA regB               
129: Branch regB (Rel 113)                    
130: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
131: WriteInstr regA charIO                   
132: Load (ImmValue 114) regA                 
133: WriteInstr regA charIO                   
134: Load (ImmValue 114) regA                 
135: WriteInstr regA charIO                   
136: Load (ImmValue 111) regA                 
137: WriteInstr regA charIO                   
138: Load (ImmValue 114) regA                 
139: WriteInstr regA charIO                   
140: Load (ImmValue 58) regA                  
141: WriteInstr regA charIO                   
142: Load (ImmValue 32) regA                  
143: WriteInstr regA charIO                   
144: Load (ImmValue 65) regA                  
145: WriteInstr regA charIO                   
146: Load (ImmValue 116) regA                 
147: WriteInstr regA charIO                   
148: Load (ImmValue 116) regA                 
149: WriteInstr regA charIO                   
150: Load (ImmValue 101) regA                 
151: WriteInstr regA charIO                   
152: Load (ImmValue 109) regA                 
153: WriteInstr regA charIO                   
154: Load (ImmValue 112) regA                 
155: WriteInstr regA charIO                   
156: Load (ImmValue 116) regA                 
157: WriteInstr regA charIO                   
158: Load (ImmValue 105) regA                 
159: WriteInstr regA charIO                   
160: Load (ImmValue 110) regA                 
161: WriteInstr regA charIO                   
162: Load (ImmValue 103) regA                 
163: WriteInstr regA charIO                   
164: Load (ImmValue 32) regA                  
165: WriteInstr regA charIO                   
166: Load (ImmValue 116) regA                 
167: WriteInstr regA charIO                   
168: Load (ImmValue 111) regA                 
169: WriteInstr regA charIO                   
170: Load (ImmValue 32) regA                  
171: WriteInstr regA charIO                   
172: Load (ImmValue 97) regA                  
173: WriteInstr regA charIO                   
174: Load (ImmValue 99) regA                  
175: WriteInstr regA charIO                   
176: Load (ImmValue 99) regA                  
177: WriteInstr regA charIO                   
178: Load (ImmValue 101) regA                 
179: WriteInstr regA charIO                   
180: Load (ImmValue 115) regA                 
181: WriteInstr regA charIO                   
182: Load (ImmValue 115) regA                 
183: WriteInstr regA charIO                   
184: Load (ImmValue 32) regA                  
185: WriteInstr regA charIO                   
186: Load (ImmValue 111) regA                 
187: WriteInstr regA charIO                   
188: Load (ImmValue 117) regA                 
189: WriteInstr regA charIO                   
190: Load (ImmValue 116) regA                 
191: WriteInstr regA charIO                   
192: Load (ImmValue 32) regA                  
193: WriteInstr regA charIO                   
194: Load (ImmValue 111) regA                 
195: WriteInstr regA charIO                   
196: Load (ImmValue 102) regA                 
197: WriteInstr regA charIO                   
198: Load (ImmValue 32) regA                  
199: WriteInstr regA charIO                   
200: Load (ImmValue 98) regA                  
201: WriteInstr regA charIO                   
202: Load (ImmValue 111) regA                 
203: WriteInstr regA charIO                   
204: Load (ImmValue 117) regA                 
205: WriteInstr regA charIO                   
206: Load (ImmValue 110) regA                 
207: WriteInstr regA charIO                   
208: Load (ImmValue 100) regA                 
209: WriteInstr regA charIO                   
210: Load (ImmValue 115) regA                 
211: WriteInstr regA charIO                   
212: Load (ImmValue 32) regA                  
213: WriteInstr regA charIO                   
214: Load (ImmValue 105) regA                 
215: WriteInstr regA charIO                   
216: Load (ImmValue 110) regA                 
217: WriteInstr regA charIO                   
218: Load (ImmValue 100) regA                 
219: WriteInstr regA charIO                   
220: Load (ImmValue 101) regA                 
221: WriteInstr regA charIO                   
222: Load (ImmValue 120) regA                 
223: WriteInstr regA charIO                   
224: Load (ImmValue 32) regA                  
225: WriteInstr regA charIO                   
226: Load (ImmValue 111) regA                 
227: WriteInstr regA charIO                   
228: Load (ImmValue 102) regA                 
229: WriteInstr regA charIO                   
230: Load (ImmValue 32) regA                  
231: WriteInstr regA charIO                   
232: Load (ImmValue 97) regA                  
233: WriteInstr regA charIO                   
234: Load (ImmValue 114) regA                 
235: WriteInstr regA charIO                   
236: Load (ImmValue 114) regA                 
237: WriteInstr regA charIO                   
238: Load (ImmValue 97) regA                  
239: WriteInstr regA charIO                   
240: Load (ImmValue 121) regA                 
241: WriteInstr regA charIO                   
242: EndProg                   
``` 