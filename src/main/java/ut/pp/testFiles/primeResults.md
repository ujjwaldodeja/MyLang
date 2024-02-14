This program checks if x is prime, or returns the smallest divisor.
Test runs:
- x = 5 => output: -1
- x = 8 => output: 2
- x = 17 => output: -1
- x = 35 => output: 5

### Output:
```
Sprockell 0 says 5
```

### Spril instructions:
```
  0: Load (ImmValue 35) regA                  --Assignment to x
  1: Store regA (DirAddr 0)                   
  2: Load (ImmValue 2) regA                   --Assignment to i
  3: Store regA (DirAddr 1)                   
  4: Load (ImmValue 0) regA                   --Assignment to stop
  5: Store regA (DirAddr 2)                   
  6: Load (DirAddr 0) regA                    --(Loading var x) // While condition check
  7: Push regA                                
  8: Load (DirAddr 1) regB                    --(Loading var i)
  9: Load (DirAddr 1) regA                    --(Loading var i)
 10: Compute Mul regA regB regA               
 11: Pop regB                                 
 12: Compute Lt regA regB regA                
 13: Push regA                                
 14: Load (DirAddr 2) regA                    --(Loading var stop)
 15: Compute Equal regA reg0 regA             
 16: Pop regB                                 
 17: Compute And regA regB regA               
 18: Compute Equal regA reg0 regA             
 19: Branch regA (Abs 75)                     
 20: Load (DirAddr 0) regA                    --(Loading var x) // Assignment to stop // While body
 21: Push regA                                
 22: Load (DirAddr 1) regB                    --(Loading var i)
 23: Load (DirAddr 0) regA                    --(Loading var x)
 24: Compute Equal regB reg0 regD             --Compute division
 25: Load (ImmValue 0) regF                   
 26: Branch regD (Abs 86)                     --Branch to error
 27: Compute GtE regA reg0 regC               
 28: Compute GtE regB reg0 regD               
 29: Load (ImmValue (-1)) regE                
 30: Branch regC (Rel 2)                      
 31: Compute Mul regA regE regA               
 32: Branch regD (Rel 2)                      
 33: Compute Mul regB regE regB               
 34: Compute Xor regC regD regC               
 35: Push regC                                
 36: Load (ImmValue 0) regE                   
 37: Load (ImmValue 1) regF                   
 38: Load (ImmValue 1) regD                   
 39: Compute GtE regF regA regC               
 40: Branch regC (Rel 3)                      
 41: Compute LShift regF regD regF            
 42: Jump (Rel (-3))                          
 43: Load (ImmValue 0) regD                   
 44: Compute Equal regF reg0 regC             
 45: Branch regC (Rel 14)                     
 46: Load (ImmValue 1) regC                   
 47: Compute LShift regE regC regE            
 48: Compute And regF regA regC               
 49: Compute Equal regC reg0 regC             
 50: Branch regC (Rel 2)                      
 51: Compute Incr regE regE regE              
 52: Compute Lt regE regB regC                
 53: Branch regC (Rel 3)                      
 54: Compute Sub regE regB regE               
 55: Compute Or regD regF regD                
 56: Load (ImmValue 1) regC                   
 57: Compute RShift regF regC regF            
 58: Jump (Rel (-14))                         
 59: Pop regC                                 
 60: Compute Equal regC reg0 regC             
 61: Branch regC (Rel 3)                      
 62: Load (ImmValue (-1)) regE                
 63: Compute Mul regE regD regD               
 64: Compute Add regD reg0 regB               
 65: Load (DirAddr 1) regA                    --(Loading var i)
 66: Compute Mul regA regB regA               
 67: Pop regB                                 
 68: Compute Equal regA regB regA             
 69: Store regA (DirAddr 2)                   
 70: Load (ImmValue 1) regB                   --Assignment to i
 71: Load (DirAddr 1) regA                    --(Loading var i)
 72: Compute Add regA regB regA               
 73: Store regA (DirAddr 1)                   
 74: Jump (Abs 6)                             
 75: Load (DirAddr 2) regA                    --(Loading var stop) // Check if condition
 76: Compute Equal regA reg0 regA             
 77: Branch regA (Abs 83)                     
 78: Load (ImmValue 1) regB                   --Print // If body
 79: Load (DirAddr 1) regA                    --(Loading var i)
 80: Compute Sub regA regB regA               
 81: WriteInstr regA numberIO                 
 82: Jump (Abs 85)                            
 83: Load (ImmValue (-1)) regA                --Print // Else body
 84: WriteInstr regA numberIO                 
 85: EndProg                                  
 86: Load (ImmValue 0) regA                   --Error Handling
 87: Compute NEq regF regA regB               
 88: Branch regB (Rel 51)                     
 89: Load (ImmValue 69) regA                  --Print error DivByZero
 90: WriteInstr regA charIO                   
 91: Load (ImmValue 114) regA                 
 92: WriteInstr regA charIO                   
 93: Load (ImmValue 114) regA                 
 94: WriteInstr regA charIO                   
 95: Load (ImmValue 111) regA                 
 96: WriteInstr regA charIO                   
 97: Load (ImmValue 114) regA                 
 98: WriteInstr regA charIO                   
 99: Load (ImmValue 58) regA                  
100: WriteInstr regA charIO                   
101: Load (ImmValue 32) regA                  
102: WriteInstr regA charIO                   
103: Load (ImmValue 67) regA                  
104: WriteInstr regA charIO                   
105: Load (ImmValue 97) regA                  
106: WriteInstr regA charIO                   
107: Load (ImmValue 110) regA                 
108: WriteInstr regA charIO                   
109: Load (ImmValue 110) regA                 
110: WriteInstr regA charIO                   
111: Load (ImmValue 111) regA                 
112: WriteInstr regA charIO                   
113: Load (ImmValue 116) regA                 
114: WriteInstr regA charIO                   
115: Load (ImmValue 32) regA                  
116: WriteInstr regA charIO                   
117: Load (ImmValue 100) regA                 
118: WriteInstr regA charIO                   
119: Load (ImmValue 105) regA                 
120: WriteInstr regA charIO                   
121: Load (ImmValue 118) regA                 
122: WriteInstr regA charIO                   
123: Load (ImmValue 105) regA                 
124: WriteInstr regA charIO                   
125: Load (ImmValue 100) regA                 
126: WriteInstr regA charIO                   
127: Load (ImmValue 101) regA                 
128: WriteInstr regA charIO                   
129: Load (ImmValue 32) regA                  
130: WriteInstr regA charIO                   
131: Load (ImmValue 98) regA                  
132: WriteInstr regA charIO                   
133: Load (ImmValue 121) regA                 
134: WriteInstr regA charIO                   
135: Load (ImmValue 32) regA                  
136: WriteInstr regA charIO                   
137: Load (ImmValue 48) regA                  
138: WriteInstr regA charIO                   
139: Load (ImmValue 1) regA                   
140: Compute NEq regF regA regB               
141: Branch regB (Rel 113)                    
142: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
143: WriteInstr regA charIO                   
144: Load (ImmValue 114) regA                 
145: WriteInstr regA charIO                   
146: Load (ImmValue 114) regA                 
147: WriteInstr regA charIO                   
148: Load (ImmValue 111) regA                 
149: WriteInstr regA charIO                   
150: Load (ImmValue 114) regA                 
151: WriteInstr regA charIO                   
152: Load (ImmValue 58) regA                  
153: WriteInstr regA charIO                   
154: Load (ImmValue 32) regA                  
155: WriteInstr regA charIO                   
156: Load (ImmValue 65) regA                  
157: WriteInstr regA charIO                   
158: Load (ImmValue 116) regA                 
159: WriteInstr regA charIO                   
160: Load (ImmValue 116) regA                 
161: WriteInstr regA charIO                   
162: Load (ImmValue 101) regA                 
163: WriteInstr regA charIO                   
164: Load (ImmValue 109) regA                 
165: WriteInstr regA charIO                   
166: Load (ImmValue 112) regA                 
167: WriteInstr regA charIO                   
168: Load (ImmValue 116) regA                 
169: WriteInstr regA charIO                   
170: Load (ImmValue 105) regA                 
171: WriteInstr regA charIO                   
172: Load (ImmValue 110) regA                 
173: WriteInstr regA charIO                   
174: Load (ImmValue 103) regA                 
175: WriteInstr regA charIO                   
176: Load (ImmValue 32) regA                  
177: WriteInstr regA charIO                   
178: Load (ImmValue 116) regA                 
179: WriteInstr regA charIO                   
180: Load (ImmValue 111) regA                 
181: WriteInstr regA charIO                   
182: Load (ImmValue 32) regA                  
183: WriteInstr regA charIO                   
184: Load (ImmValue 97) regA                  
185: WriteInstr regA charIO                   
186: Load (ImmValue 99) regA                  
187: WriteInstr regA charIO                   
188: Load (ImmValue 99) regA                  
189: WriteInstr regA charIO                   
190: Load (ImmValue 101) regA                 
191: WriteInstr regA charIO                   
192: Load (ImmValue 115) regA                 
193: WriteInstr regA charIO                   
194: Load (ImmValue 115) regA                 
195: WriteInstr regA charIO                   
196: Load (ImmValue 32) regA                  
197: WriteInstr regA charIO                   
198: Load (ImmValue 111) regA                 
199: WriteInstr regA charIO                   
200: Load (ImmValue 117) regA                 
201: WriteInstr regA charIO                   
202: Load (ImmValue 116) regA                 
203: WriteInstr regA charIO                   
204: Load (ImmValue 32) regA                  
205: WriteInstr regA charIO                   
206: Load (ImmValue 111) regA                 
207: WriteInstr regA charIO                   
208: Load (ImmValue 102) regA                 
209: WriteInstr regA charIO                   
210: Load (ImmValue 32) regA                  
211: WriteInstr regA charIO                   
212: Load (ImmValue 98) regA                  
213: WriteInstr regA charIO                   
214: Load (ImmValue 111) regA                 
215: WriteInstr regA charIO                   
216: Load (ImmValue 117) regA                 
217: WriteInstr regA charIO                   
218: Load (ImmValue 110) regA                 
219: WriteInstr regA charIO                   
220: Load (ImmValue 100) regA                 
221: WriteInstr regA charIO                   
222: Load (ImmValue 115) regA                 
223: WriteInstr regA charIO                   
224: Load (ImmValue 32) regA                  
225: WriteInstr regA charIO                   
226: Load (ImmValue 105) regA                 
227: WriteInstr regA charIO                   
228: Load (ImmValue 110) regA                 
229: WriteInstr regA charIO                   
230: Load (ImmValue 100) regA                 
231: WriteInstr regA charIO                   
232: Load (ImmValue 101) regA                 
233: WriteInstr regA charIO                   
234: Load (ImmValue 120) regA                 
235: WriteInstr regA charIO                   
236: Load (ImmValue 32) regA                  
237: WriteInstr regA charIO                   
238: Load (ImmValue 111) regA                 
239: WriteInstr regA charIO                   
240: Load (ImmValue 102) regA                 
241: WriteInstr regA charIO                   
242: Load (ImmValue 32) regA                  
243: WriteInstr regA charIO                   
244: Load (ImmValue 97) regA                  
245: WriteInstr regA charIO                   
246: Load (ImmValue 114) regA                 
247: WriteInstr regA charIO                   
248: Load (ImmValue 114) regA                 
249: WriteInstr regA charIO                   
250: Load (ImmValue 97) regA                  
251: WriteInstr regA charIO                   
252: Load (ImmValue 121) regA                 
253: WriteInstr regA charIO                   
254: EndProg            
``` 