Implementation of Petersons algorithm. Can theoretically print the values in reverse order as well. (But Sprockell is deterministic)

### Output:
```
Sprockell 0 says 1
Sprockell 1 says 2
```

### Spril instructions:
```
  0: Branch regSprID (Abs 56)                 --Make all threads other than main jump to the idle loop
  1: WriteInstr reg0 (DirAddr 0)              --Declaration of flag
  2: WriteInstr reg0 (DirAddr 1)              
  3: WriteInstr reg0 (DirAddr 2)              --Declaration of turn
  4: Load (ImmValue 0) regA                   --Assignment of array to flag
  5: WriteInstr regA (DirAddr 0)              
  6: Load (ImmValue 0) regA                   
  7: WriteInstr regA (DirAddr 1)              
  8: Load (ImmValue 32) regA                  --Fork to thread2
  9: WriteInstr regA (DirAddr 3)              
 10: Load (ImmValue 1) regA                   --Assignment to flag
 11: Store regA (DirAddr 0)                   
 12: Load (ImmValue 1) regA                   --Assignment to turn
 13: WriteInstr regA (DirAddr 2)              
 14: Load (ImmValue 1) regB                   --While condition check
 15: ReadInstr (DirAddr 2)                    --(Loading shared var turn)
 16: Receive regA                             
 17: Compute Equal regA regB regA             
 18: Push regA                                
 19: Load (ImmValue 1) regB                   
 20: Load (DirAddr 1) regA                    --(Loading array index flag at offset 1)
 21: Compute Equal regA regB regA             
 22: Pop regB                                 
 23: Compute And regA regB regA               
 24: Compute Equal regA reg0 regA             
 25: Branch regA (Abs 27)                     
 26: Jump (Abs 14)                            
 27: Load (ImmValue 1) regA                   --Print
 28: WriteInstr regA numberIO                 
 29: Load (ImmValue 0) regA                   --Assignment to flag
 30: Store regA (DirAddr 0)                   
 31: Jump (Abs 66)                            
 32: Load (ImmValue 1) regA                   --Assignment to flag // Start of thread thread2
 33: Store regA (DirAddr 1)                   
 34: Load (ImmValue 0) regA                   --Assignment to turn
 35: WriteInstr regA (DirAddr 2)              
 36: Load (ImmValue 0) regB                   --While condition check
 37: ReadInstr (DirAddr 2)                    --(Loading shared var turn)
 38: Receive regA                             
 39: Compute Equal regA regB regA             
 40: Push regA                                
 41: Load (ImmValue 1) regB                   
 42: Load (DirAddr 0) regA                    --(Loading array index flag at offset 0)
 43: Compute Equal regA regB regA             
 44: Pop regB                                 
 45: Compute And regA regB regA               
 46: Compute Equal regA reg0 regA             
 47: Branch regA (Abs 49)                     
 48: Jump (Abs 36)                            
 49: Load (ImmValue 2) regA                   --Print
 50: WriteInstr regA numberIO                 
 51: Load (ImmValue 0) regA                   --Assignment to flag
 52: Store regA (DirAddr 1)                   
 53: Load (ImmValue (-1)) regA                --Set thread_done flag for join(thread2)
 54: WriteInstr regA (DirAddr 3)              
 55: Jump (Abs 66)                            
 56: Load (ImmValue 1) regA                   --Get thread memory location for thread 1
 57: Compute NEq regA regSprID regA           
 58: Branch regA (Rel 3)                      
 59: Load (ImmValue 3) regA                   
 60: Jump (Abs 61)                            
 61: ReadInstr (IndAddr regA)                 --Thread pool idle loop
 62: Receive regB                             
 63: Compute Equal regB reg0 regC             
 64: Branch regC (Rel (-3))                   
 65: Jump (Ind regB)                          
 66: EndProg                                  
 67: Load (ImmValue 0) regA                   --Error Handling
 68: Compute NEq regF regA regB               
 69: Branch regB (Rel 51)                     
 70: Load (ImmValue 69) regA                  --Print error DivByZero
 71: WriteInstr regA charIO                   
 72: Load (ImmValue 114) regA                 
 73: WriteInstr regA charIO                   
 74: Load (ImmValue 114) regA                 
 75: WriteInstr regA charIO                   
 76: Load (ImmValue 111) regA                 
 77: WriteInstr regA charIO                   
 78: Load (ImmValue 114) regA                 
 79: WriteInstr regA charIO                   
 80: Load (ImmValue 58) regA                  
 81: WriteInstr regA charIO                   
 82: Load (ImmValue 32) regA                  
 83: WriteInstr regA charIO                   
 84: Load (ImmValue 67) regA                  
 85: WriteInstr regA charIO                   
 86: Load (ImmValue 97) regA                  
 87: WriteInstr regA charIO                   
 88: Load (ImmValue 110) regA                 
 89: WriteInstr regA charIO                   
 90: Load (ImmValue 110) regA                 
 91: WriteInstr regA charIO                   
 92: Load (ImmValue 111) regA                 
 93: WriteInstr regA charIO                   
 94: Load (ImmValue 116) regA                 
 95: WriteInstr regA charIO                   
 96: Load (ImmValue 32) regA                  
 97: WriteInstr regA charIO                   
 98: Load (ImmValue 100) regA                 
 99: WriteInstr regA charIO                   
100: Load (ImmValue 105) regA                 
101: WriteInstr regA charIO                   
102: Load (ImmValue 118) regA                 
103: WriteInstr regA charIO                   
104: Load (ImmValue 105) regA                 
105: WriteInstr regA charIO                   
106: Load (ImmValue 100) regA                 
107: WriteInstr regA charIO                   
108: Load (ImmValue 101) regA                 
109: WriteInstr regA charIO                   
110: Load (ImmValue 32) regA                  
111: WriteInstr regA charIO                   
112: Load (ImmValue 98) regA                  
113: WriteInstr regA charIO                   
114: Load (ImmValue 121) regA                 
115: WriteInstr regA charIO                   
116: Load (ImmValue 32) regA                  
117: WriteInstr regA charIO                   
118: Load (ImmValue 48) regA                  
119: WriteInstr regA charIO                   
120: Load (ImmValue 1) regA                   
121: Compute NEq regF regA regB               
122: Branch regB (Rel 113)                    
123: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
124: WriteInstr regA charIO                   
125: Load (ImmValue 114) regA                 
126: WriteInstr regA charIO                   
127: Load (ImmValue 114) regA                 
128: WriteInstr regA charIO                   
129: Load (ImmValue 111) regA                 
130: WriteInstr regA charIO                   
131: Load (ImmValue 114) regA                 
132: WriteInstr regA charIO                   
133: Load (ImmValue 58) regA                  
134: WriteInstr regA charIO                   
135: Load (ImmValue 32) regA                  
136: WriteInstr regA charIO                   
137: Load (ImmValue 65) regA                  
138: WriteInstr regA charIO                   
139: Load (ImmValue 116) regA                 
140: WriteInstr regA charIO                   
141: Load (ImmValue 116) regA                 
142: WriteInstr regA charIO                   
143: Load (ImmValue 101) regA                 
144: WriteInstr regA charIO                   
145: Load (ImmValue 109) regA                 
146: WriteInstr regA charIO                   
147: Load (ImmValue 112) regA                 
148: WriteInstr regA charIO                   
149: Load (ImmValue 116) regA                 
150: WriteInstr regA charIO                   
151: Load (ImmValue 105) regA                 
152: WriteInstr regA charIO                   
153: Load (ImmValue 110) regA                 
154: WriteInstr regA charIO                   
155: Load (ImmValue 103) regA                 
156: WriteInstr regA charIO                   
157: Load (ImmValue 32) regA                  
158: WriteInstr regA charIO                   
159: Load (ImmValue 116) regA                 
160: WriteInstr regA charIO                   
161: Load (ImmValue 111) regA                 
162: WriteInstr regA charIO                   
163: Load (ImmValue 32) regA                  
164: WriteInstr regA charIO                   
165: Load (ImmValue 97) regA                  
166: WriteInstr regA charIO                   
167: Load (ImmValue 99) regA                  
168: WriteInstr regA charIO                   
169: Load (ImmValue 99) regA                  
170: WriteInstr regA charIO                   
171: Load (ImmValue 101) regA                 
172: WriteInstr regA charIO                   
173: Load (ImmValue 115) regA                 
174: WriteInstr regA charIO                   
175: Load (ImmValue 115) regA                 
176: WriteInstr regA charIO                   
177: Load (ImmValue 32) regA                  
178: WriteInstr regA charIO                   
179: Load (ImmValue 111) regA                 
180: WriteInstr regA charIO                   
181: Load (ImmValue 117) regA                 
182: WriteInstr regA charIO                   
183: Load (ImmValue 116) regA                 
184: WriteInstr regA charIO                   
185: Load (ImmValue 32) regA                  
186: WriteInstr regA charIO                   
187: Load (ImmValue 111) regA                 
188: WriteInstr regA charIO                   
189: Load (ImmValue 102) regA                 
190: WriteInstr regA charIO                   
191: Load (ImmValue 32) regA                  
192: WriteInstr regA charIO                   
193: Load (ImmValue 98) regA                  
194: WriteInstr regA charIO                   
195: Load (ImmValue 111) regA                 
196: WriteInstr regA charIO                   
197: Load (ImmValue 117) regA                 
198: WriteInstr regA charIO                   
199: Load (ImmValue 110) regA                 
200: WriteInstr regA charIO                   
201: Load (ImmValue 100) regA                 
202: WriteInstr regA charIO                   
203: Load (ImmValue 115) regA                 
204: WriteInstr regA charIO                   
205: Load (ImmValue 32) regA                  
206: WriteInstr regA charIO                   
207: Load (ImmValue 105) regA                 
208: WriteInstr regA charIO                   
209: Load (ImmValue 110) regA                 
210: WriteInstr regA charIO                   
211: Load (ImmValue 100) regA                 
212: WriteInstr regA charIO                   
213: Load (ImmValue 101) regA                 
214: WriteInstr regA charIO                   
215: Load (ImmValue 120) regA                 
216: WriteInstr regA charIO                   
217: Load (ImmValue 32) regA                  
218: WriteInstr regA charIO                   
219: Load (ImmValue 111) regA                 
220: WriteInstr regA charIO                   
221: Load (ImmValue 102) regA                 
222: WriteInstr regA charIO                   
223: Load (ImmValue 32) regA                  
224: WriteInstr regA charIO                   
225: Load (ImmValue 97) regA                  
226: WriteInstr regA charIO                   
227: Load (ImmValue 114) regA                 
228: WriteInstr regA charIO                   
229: Load (ImmValue 114) regA                 
230: WriteInstr regA charIO                   
231: Load (ImmValue 97) regA                  
232: WriteInstr regA charIO                   
233: Load (ImmValue 121) regA                 
234: WriteInstr regA charIO                   
235: EndProg            
``` 