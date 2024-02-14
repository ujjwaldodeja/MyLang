Note that our program gives a warning that p is not initialized in the if condition, but no warning that it's not initialized at 
the end of the program, because it MIGHT be initialized in the if body.

### Output:
```
Warning at line 13:6 -> Variable q was not initialized
Warning at line 15:5 -> Variable p was not initialized
Sprockell 0 says 3
Sprockell 0 says 0
Sprockell 0 says 1
```

### Spril instructions:
```
  0: Load (ImmValue 1) regA                   --Assignment to a
  1: Store regA (DirAddr 0)                   
  2: Load (ImmValue 1) regA                   --Assignment to d
  3: Store regA (DirAddr 1)                   
  4: Load (ImmValue 1) regA                   --Assignment of array to n
  5: Store regA (DirAddr 2)                   
  6: Load (ImmValue 2) regA                   
  7: Store regA (DirAddr 3)                   
  8: Load (ImmValue 3) regA                   
  9: Store regA (DirAddr 4)                   
 10: Load (ImmValue 4) regA                   
 11: Store regA (DirAddr 5)                   
 12: Load (ImmValue 5) regA                   
 13: Store regA (DirAddr 6)                   
 14: Load (ImmValue 0) regA                   --Assignment of array to m
 15: Store regA (DirAddr 7)                   
 16: Load (ImmValue 1) regA                   
 17: Store regA (DirAddr 8)                   
 18: Load (ImmValue 0) regA                   
 19: Store regA (DirAddr 9)                   
 20: Load (ImmValue 1) regA                   
 21: Store regA (DirAddr 10)                  
 22: Load (ImmValue 0) regA                   
 23: Store regA (DirAddr 11)                  
 24: Load (DirAddr 1) regA                    --(Loading var d) // Assignment to i
 25: Store regA (DirAddr 12)                  
 26: Load (DirAddr 7) regA                    --(Loading array index m at offset 0) // Assignment to r
 27: Store regA (DirAddr 13)                  
 28: Load (ImmValue 2) regB                   --Calculating offset for reading from variable n // Assignment to x
 29: Load (ImmValue 4) regA                   
 30: Compute Equal regB reg0 regD             --Compute division
 31: Load (ImmValue 0) regF                   
 32: Branch regD (Abs 96)                     --Branch to error
 33: Compute GtE regA reg0 regC               
 34: Compute GtE regB reg0 regD               
 35: Load (ImmValue (-1)) regE                
 36: Branch regC (Rel 2)                      
 37: Compute Mul regA regE regA               
 38: Branch regD (Rel 2)                      
 39: Compute Mul regB regE regB               
 40: Compute Xor regC regD regC               
 41: Push regC                                
 42: Load (ImmValue 0) regE                   
 43: Load (ImmValue 1) regF                   
 44: Load (ImmValue 1) regD                   
 45: Compute GtE regF regA regC               
 46: Branch regC (Rel 3)                      
 47: Compute LShift regF regD regF            
 48: Jump (Rel (-3))                          
 49: Load (ImmValue 0) regD                   
 50: Compute Equal regF reg0 regC             
 51: Branch regC (Rel 14)                     
 52: Load (ImmValue 1) regC                   
 53: Compute LShift regE regC regE            
 54: Compute And regF regA regC               
 55: Compute Equal regC reg0 regC             
 56: Branch regC (Rel 2)                      
 57: Compute Incr regE regE regE              
 58: Compute Lt regE regB regC                
 59: Branch regC (Rel 3)                      
 60: Compute Sub regE regB regE               
 61: Compute Or regD regF regD                
 62: Load (ImmValue 1) regC                   
 63: Compute RShift regF regC regF            
 64: Jump (Rel (-14))                         
 65: Pop regC                                 
 66: Compute Equal regC reg0 regC             
 67: Branch regC (Rel 3)                      
 68: Load (ImmValue (-1)) regE                
 69: Compute Mul regE regD regD               
 70: Compute Add regD reg0 regA               
 71: Compute Lt regA reg0 regB                
 72: Load (ImmValue 4) regC                   
 73: Compute Gt regA regC regC                
 74: Compute Or regB regC regB                
 75: Load (ImmValue 1) regF                   
 76: Branch regB (Abs 96)                     --Branch to error
 77: Load (ImmValue 2) regB                   
 78: Compute Add regA regB regA               
 79: Load (IndAddr regA) regA                 --(Loading array index n at calculated offset)
 80: Store regA (DirAddr 14)                  
 81: Load (DirAddr 14) regA                   --(Loading var x) // Print
 82: WriteInstr regA numberIO                 
 83: Store reg0 (DirAddr 15)                  --Declaration of q
 84: Load (DirAddr 15) regA                   --(Loading var q) // Print
 85: WriteInstr regA numberIO                 
 86: Store reg0 (DirAddr 16)                  --Declaration of p
 87: Load (DirAddr 16) regA                   --(Loading var p) // Check if condition
 88: Compute Equal regA reg0 regA             
 89: Compute Equal regA reg0 regA             
 90: Branch regA (Abs 93)                     
 91: Load (ImmValue 1) regA                   --Assignment to p // If body
 92: Store regA (DirAddr 16)                  
 93: Load (DirAddr 16) regA                   --(Loading var p) // Print
 94: WriteInstr regA numberIO                 
 95: EndProg                                  
 96: Load (ImmValue 0) regA                   --Error Handling
 97: Compute NEq regF regA regB               
 98: Branch regB (Rel 51)                     
 99: Load (ImmValue 69) regA                  --Print error DivByZero
100: WriteInstr regA charIO                   
101: Load (ImmValue 114) regA                 
102: WriteInstr regA charIO                   
103: Load (ImmValue 114) regA                 
104: WriteInstr regA charIO                   
105: Load (ImmValue 111) regA                 
106: WriteInstr regA charIO                   
107: Load (ImmValue 114) regA                 
108: WriteInstr regA charIO                   
109: Load (ImmValue 58) regA                  
110: WriteInstr regA charIO                   
111: Load (ImmValue 32) regA                  
112: WriteInstr regA charIO                   
113: Load (ImmValue 67) regA                  
114: WriteInstr regA charIO                   
115: Load (ImmValue 97) regA                  
116: WriteInstr regA charIO                   
117: Load (ImmValue 110) regA                 
118: WriteInstr regA charIO                   
119: Load (ImmValue 110) regA                 
120: WriteInstr regA charIO                   
121: Load (ImmValue 111) regA                 
122: WriteInstr regA charIO                   
123: Load (ImmValue 116) regA                 
124: WriteInstr regA charIO                   
125: Load (ImmValue 32) regA                  
126: WriteInstr regA charIO                   
127: Load (ImmValue 100) regA                 
128: WriteInstr regA charIO                   
129: Load (ImmValue 105) regA                 
130: WriteInstr regA charIO                   
131: Load (ImmValue 118) regA                 
132: WriteInstr regA charIO                   
133: Load (ImmValue 105) regA                 
134: WriteInstr regA charIO                   
135: Load (ImmValue 100) regA                 
136: WriteInstr regA charIO                   
137: Load (ImmValue 101) regA                 
138: WriteInstr regA charIO                   
139: Load (ImmValue 32) regA                  
140: WriteInstr regA charIO                   
141: Load (ImmValue 98) regA                  
142: WriteInstr regA charIO                   
143: Load (ImmValue 121) regA                 
144: WriteInstr regA charIO                   
145: Load (ImmValue 32) regA                  
146: WriteInstr regA charIO                   
147: Load (ImmValue 48) regA                  
148: WriteInstr regA charIO                   
149: Load (ImmValue 1) regA                   
150: Compute NEq regF regA regB               
151: Branch regB (Rel 113)                    
152: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
153: WriteInstr regA charIO                   
154: Load (ImmValue 114) regA                 
155: WriteInstr regA charIO                   
156: Load (ImmValue 114) regA                 
157: WriteInstr regA charIO                   
158: Load (ImmValue 111) regA                 
159: WriteInstr regA charIO                   
160: Load (ImmValue 114) regA                 
161: WriteInstr regA charIO                   
162: Load (ImmValue 58) regA                  
163: WriteInstr regA charIO                   
164: Load (ImmValue 32) regA                  
165: WriteInstr regA charIO                   
166: Load (ImmValue 65) regA                  
167: WriteInstr regA charIO                   
168: Load (ImmValue 116) regA                 
169: WriteInstr regA charIO                   
170: Load (ImmValue 116) regA                 
171: WriteInstr regA charIO                   
172: Load (ImmValue 101) regA                 
173: WriteInstr regA charIO                   
174: Load (ImmValue 109) regA                 
175: WriteInstr regA charIO                   
176: Load (ImmValue 112) regA                 
177: WriteInstr regA charIO                   
178: Load (ImmValue 116) regA                 
179: WriteInstr regA charIO                   
180: Load (ImmValue 105) regA                 
181: WriteInstr regA charIO                   
182: Load (ImmValue 110) regA                 
183: WriteInstr regA charIO                   
184: Load (ImmValue 103) regA                 
185: WriteInstr regA charIO                   
186: Load (ImmValue 32) regA                  
187: WriteInstr regA charIO                   
188: Load (ImmValue 116) regA                 
189: WriteInstr regA charIO                   
190: Load (ImmValue 111) regA                 
191: WriteInstr regA charIO                   
192: Load (ImmValue 32) regA                  
193: WriteInstr regA charIO                   
194: Load (ImmValue 97) regA                  
195: WriteInstr regA charIO                   
196: Load (ImmValue 99) regA                  
197: WriteInstr regA charIO                   
198: Load (ImmValue 99) regA                  
199: WriteInstr regA charIO                   
200: Load (ImmValue 101) regA                 
201: WriteInstr regA charIO                   
202: Load (ImmValue 115) regA                 
203: WriteInstr regA charIO                   
204: Load (ImmValue 115) regA                 
205: WriteInstr regA charIO                   
206: Load (ImmValue 32) regA                  
207: WriteInstr regA charIO                   
208: Load (ImmValue 111) regA                 
209: WriteInstr regA charIO                   
210: Load (ImmValue 117) regA                 
211: WriteInstr regA charIO                   
212: Load (ImmValue 116) regA                 
213: WriteInstr regA charIO                   
214: Load (ImmValue 32) regA                  
215: WriteInstr regA charIO                   
216: Load (ImmValue 111) regA                 
217: WriteInstr regA charIO                   
218: Load (ImmValue 102) regA                 
219: WriteInstr regA charIO                   
220: Load (ImmValue 32) regA                  
221: WriteInstr regA charIO                   
222: Load (ImmValue 98) regA                  
223: WriteInstr regA charIO                   
224: Load (ImmValue 111) regA                 
225: WriteInstr regA charIO                   
226: Load (ImmValue 117) regA                 
227: WriteInstr regA charIO                   
228: Load (ImmValue 110) regA                 
229: WriteInstr regA charIO                   
230: Load (ImmValue 100) regA                 
231: WriteInstr regA charIO                   
232: Load (ImmValue 115) regA                 
233: WriteInstr regA charIO                   
234: Load (ImmValue 32) regA                  
235: WriteInstr regA charIO                   
236: Load (ImmValue 105) regA                 
237: WriteInstr regA charIO                   
238: Load (ImmValue 110) regA                 
239: WriteInstr regA charIO                   
240: Load (ImmValue 100) regA                 
241: WriteInstr regA charIO                   
242: Load (ImmValue 101) regA                 
243: WriteInstr regA charIO                   
244: Load (ImmValue 120) regA                 
245: WriteInstr regA charIO                   
246: Load (ImmValue 32) regA                  
247: WriteInstr regA charIO                   
248: Load (ImmValue 111) regA                 
249: WriteInstr regA charIO                   
250: Load (ImmValue 102) regA                 
251: WriteInstr regA charIO                   
252: Load (ImmValue 32) regA                  
253: WriteInstr regA charIO                   
254: Load (ImmValue 97) regA                  
255: WriteInstr regA charIO                   
256: Load (ImmValue 114) regA                 
257: WriteInstr regA charIO                   
258: Load (ImmValue 114) regA                 
259: WriteInstr regA charIO                   
260: Load (ImmValue 97) regA                  
261: WriteInstr regA charIO                   
262: Load (ImmValue 121) regA                 
263: WriteInstr regA charIO                   
264: EndProg                                  
``` 