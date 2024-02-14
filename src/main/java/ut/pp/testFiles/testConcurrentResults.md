### Output:
```
Sprockell 0 says 8
```

### Spril instructions:
```
  0: Branch regSprID (Abs 50)                 --Make all threads other than main jump to the idle loop
  1: Load (ImmValue 3) regA                   --Assignment to b
  2: Store regA (DirAddr 1)                   
  3: Load (ImmValue (-1)) regA                --Fork to thread2
  4: WriteInstr regA (DirAddr 0)              
  5: Load (ImmValue 3) regA                   --Assignment to c
  6: Store regA (DirAddr 2)                   
  7: Load (ImmValue 8) regA                   --Assignment to a
  8: Store regA (DirAddr 0)                   
  9: Load (DirAddr 0) regA                    --(Loading var a) // Print
 10: WriteInstr regA numberIO                 
 11: Jump (Abs 70)                            
 12: Load (ImmValue 7) regA                   --Assignment to a // Start of thread thread3
 13: Store regA (DirAddr 0)                   
 14: Load (ImmValue 8) regA                   --Assignment to b
 15: Store regA (DirAddr 1)                   
 16: Load (ImmValue 1) regA                   --Check if condition
 17: Compute Equal regA reg0 regA             
 18: Branch regA (Abs 27)                     
 19: Load (ImmValue 0) regA                   --Assignment to c // If body
 20: Store regA (DirAddr 2)                   
 21: Store reg0 (DirAddr 3)                   --Declaration of d
 22: Store reg0 (DirAddr 4)                   --Declaration of a
 23: Load (ImmValue 3) regB                   --Print
 24: Load (DirAddr 4) regA                    --(Loading var a)
 25: Compute Mul regA regB regA               
 26: WriteInstr regA numberIO                 
 27: Load (ImmValue (-1)) regA                --Set thread_done flag for join(thread3)
 28: WriteInstr regA (DirAddr 1)              
 29: Jump (Abs 70)                            
 30: Load (ImmValue 12) regA                  --Fork to thread3
 31: WriteInstr regA (DirAddr 1)              
 32: Load (ImmValue 9) regA                   --Assignment to a
 33: Store regA (DirAddr 0)                   
 34: Load (ImmValue 43) regA                  --Fork to thread4
 35: WriteInstr regA (DirAddr 2)              
 36: Load (ImmValue 2) regB                   --Print
 37: Load (DirAddr 0) regA                    --(Loading var a)
 38: Compute Mul regA regB regA               
 39: WriteInstr regA numberIO                 
 40: Load (ImmValue (-1)) regA                --Set thread_done flag for join(thread2)
 41: WriteInstr regA (DirAddr 0)              
 42: Jump (Abs 70)                            
 43: Load (ImmValue 10) regA                  --Assignment to a // Start of thread thread4
 44: Store regA (DirAddr 0)                   
 45: Load (DirAddr 0) regA                    --(Loading var a) // Print
 46: WriteInstr regA numberIO                 
 47: Load (ImmValue (-1)) regA                --Set thread_done flag for join(thread4)
 48: WriteInstr regA (DirAddr 2)              
 49: Jump (Abs 70)                            
 50: Load (ImmValue 1) regA                   --Get thread memory location for thread 1
 51: Compute NEq regA regSprID regA           
 52: Branch regA (Rel 3)                      
 53: Load (ImmValue 1) regA                   
 54: Jump (Abs 65)                            
 55: Load (ImmValue 2) regA                   --Get thread memory location for thread 2
 56: Compute NEq regA regSprID regA           
 57: Branch regA (Rel 3)                      
 58: Load (ImmValue 0) regA                   
 59: Jump (Abs 65)                            
 60: Load (ImmValue 3) regA                   --Get thread memory location for thread 3
 61: Compute NEq regA regSprID regA           
 62: Branch regA (Rel 3)                      
 63: Load (ImmValue 2) regA                   
 64: Jump (Abs 65)                            
 65: ReadInstr (IndAddr regA)                 --Thread pool idle loop
 66: Receive regB                             
 67: Compute Equal regB reg0 regC             
 68: Branch regC (Rel (-3))                   
 69: Jump (Ind regB)                          
 70: EndProg                                  
 71: Load (ImmValue 0) regA                   --Error Handling
 72: Compute NEq regF regA regB               
 73: Branch regB (Rel 51)                     
 74: Load (ImmValue 69) regA                  --Print error DivByZero
 75: WriteInstr regA charIO                   
 76: Load (ImmValue 114) regA                 
 77: WriteInstr regA charIO                   
 78: Load (ImmValue 114) regA                 
 79: WriteInstr regA charIO                   
 80: Load (ImmValue 111) regA                 
 81: WriteInstr regA charIO                   
 82: Load (ImmValue 114) regA                 
 83: WriteInstr regA charIO                   
 84: Load (ImmValue 58) regA                  
 85: WriteInstr regA charIO                   
 86: Load (ImmValue 32) regA                  
 87: WriteInstr regA charIO                   
 88: Load (ImmValue 67) regA                  
 89: WriteInstr regA charIO                   
 90: Load (ImmValue 97) regA                  
 91: WriteInstr regA charIO                   
 92: Load (ImmValue 110) regA                 
 93: WriteInstr regA charIO                   
 94: Load (ImmValue 110) regA                 
 95: WriteInstr regA charIO                   
 96: Load (ImmValue 111) regA                 
 97: WriteInstr regA charIO                   
 98: Load (ImmValue 116) regA                 
 99: WriteInstr regA charIO                   
100: Load (ImmValue 32) regA                  
101: WriteInstr regA charIO                   
102: Load (ImmValue 100) regA                 
103: WriteInstr regA charIO                   
104: Load (ImmValue 105) regA                 
105: WriteInstr regA charIO                   
106: Load (ImmValue 118) regA                 
107: WriteInstr regA charIO                   
108: Load (ImmValue 105) regA                 
109: WriteInstr regA charIO                   
110: Load (ImmValue 100) regA                 
111: WriteInstr regA charIO                   
112: Load (ImmValue 101) regA                 
113: WriteInstr regA charIO                   
114: Load (ImmValue 32) regA                  
115: WriteInstr regA charIO                   
116: Load (ImmValue 98) regA                  
117: WriteInstr regA charIO                   
118: Load (ImmValue 121) regA                 
119: WriteInstr regA charIO                   
120: Load (ImmValue 32) regA                  
121: WriteInstr regA charIO                   
122: Load (ImmValue 48) regA                  
123: WriteInstr regA charIO                   
124: Load (ImmValue 1) regA                   
125: Compute NEq regF regA regB               
126: Branch regB (Rel 113)                    
127: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
128: WriteInstr regA charIO                   
129: Load (ImmValue 114) regA                 
130: WriteInstr regA charIO                   
131: Load (ImmValue 114) regA                 
132: WriteInstr regA charIO                   
133: Load (ImmValue 111) regA                 
134: WriteInstr regA charIO                   
135: Load (ImmValue 114) regA                 
136: WriteInstr regA charIO                   
137: Load (ImmValue 58) regA                  
138: WriteInstr regA charIO                   
139: Load (ImmValue 32) regA                  
140: WriteInstr regA charIO                   
141: Load (ImmValue 65) regA                  
142: WriteInstr regA charIO                   
143: Load (ImmValue 116) regA                 
144: WriteInstr regA charIO                   
145: Load (ImmValue 116) regA                 
146: WriteInstr regA charIO                   
147: Load (ImmValue 101) regA                 
148: WriteInstr regA charIO                   
149: Load (ImmValue 109) regA                 
150: WriteInstr regA charIO                   
151: Load (ImmValue 112) regA                 
152: WriteInstr regA charIO                   
153: Load (ImmValue 116) regA                 
154: WriteInstr regA charIO                   
155: Load (ImmValue 105) regA                 
156: WriteInstr regA charIO                   
157: Load (ImmValue 110) regA                 
158: WriteInstr regA charIO                   
159: Load (ImmValue 103) regA                 
160: WriteInstr regA charIO                   
161: Load (ImmValue 32) regA                  
162: WriteInstr regA charIO                   
163: Load (ImmValue 116) regA                 
164: WriteInstr regA charIO                   
165: Load (ImmValue 111) regA                 
166: WriteInstr regA charIO                   
167: Load (ImmValue 32) regA                  
168: WriteInstr regA charIO                   
169: Load (ImmValue 97) regA                  
170: WriteInstr regA charIO                   
171: Load (ImmValue 99) regA                  
172: WriteInstr regA charIO                   
173: Load (ImmValue 99) regA                  
174: WriteInstr regA charIO                   
175: Load (ImmValue 101) regA                 
176: WriteInstr regA charIO                   
177: Load (ImmValue 115) regA                 
178: WriteInstr regA charIO                   
179: Load (ImmValue 115) regA                 
180: WriteInstr regA charIO                   
181: Load (ImmValue 32) regA                  
182: WriteInstr regA charIO                   
183: Load (ImmValue 111) regA                 
184: WriteInstr regA charIO                   
185: Load (ImmValue 117) regA                 
186: WriteInstr regA charIO                   
187: Load (ImmValue 116) regA                 
188: WriteInstr regA charIO                   
189: Load (ImmValue 32) regA                  
190: WriteInstr regA charIO                   
191: Load (ImmValue 111) regA                 
192: WriteInstr regA charIO                   
193: Load (ImmValue 102) regA                 
194: WriteInstr regA charIO                   
195: Load (ImmValue 32) regA                  
196: WriteInstr regA charIO                   
197: Load (ImmValue 98) regA                  
198: WriteInstr regA charIO                   
199: Load (ImmValue 111) regA                 
200: WriteInstr regA charIO                   
201: Load (ImmValue 117) regA                 
202: WriteInstr regA charIO                   
203: Load (ImmValue 110) regA                 
204: WriteInstr regA charIO                   
205: Load (ImmValue 100) regA                 
206: WriteInstr regA charIO                   
207: Load (ImmValue 115) regA                 
208: WriteInstr regA charIO                   
209: Load (ImmValue 32) regA                  
210: WriteInstr regA charIO                   
211: Load (ImmValue 105) regA                 
212: WriteInstr regA charIO                   
213: Load (ImmValue 110) regA                 
214: WriteInstr regA charIO                   
215: Load (ImmValue 100) regA                 
216: WriteInstr regA charIO                   
217: Load (ImmValue 101) regA                 
218: WriteInstr regA charIO                   
219: Load (ImmValue 120) regA                 
220: WriteInstr regA charIO                   
221: Load (ImmValue 32) regA                  
222: WriteInstr regA charIO                   
223: Load (ImmValue 111) regA                 
224: WriteInstr regA charIO                   
225: Load (ImmValue 102) regA                 
226: WriteInstr regA charIO                   
227: Load (ImmValue 32) regA                  
228: WriteInstr regA charIO                   
229: Load (ImmValue 97) regA                  
230: WriteInstr regA charIO                   
231: Load (ImmValue 114) regA                 
232: WriteInstr regA charIO                   
233: Load (ImmValue 114) regA                 
234: WriteInstr regA charIO                   
235: Load (ImmValue 97) regA                  
236: WriteInstr regA charIO                   
237: Load (ImmValue 121) regA                 
238: WriteInstr regA charIO                   
239: EndProg           
``` 