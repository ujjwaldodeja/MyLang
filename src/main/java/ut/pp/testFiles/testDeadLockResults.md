### Output:
```
...
```

### Spril instructions:
```
  0: Branch regSprID (Abs 40)                 --Make all threads other than main jump to the idle loop
  1: Load (ImmValue 6) regA                   --Fork to t2
  2: WriteInstr regA (DirAddr 0)              
  3: Load (ImmValue 23) regA                  --Fork to t3
  4: WriteInstr regA (DirAddr 3)              
  5: Jump (Abs 55)                            
  6: TestAndSet (DirAddr 1)                   --Try to get lock(l1) // Start of thread t2
  7: Receive regA                             
  8: Compute Equal regA reg0 regA             
  9: Branch regA (Abs 6)                      
 10: TestAndSet (DirAddr 2)                   --Try to get lock(l2)
 11: Receive regA                             
 12: Compute Equal regA reg0 regA             
 13: Branch regA (Abs 10)                     
 14: Load (ImmValue 2) regA                   --Print
 15: WriteInstr regA numberIO                 
 16: Load (ImmValue 0) regA                   --Release lock(l1)
 17: WriteInstr regA (DirAddr 1)              
 18: Load (ImmValue 0) regA                   --Release lock(l2)
 19: WriteInstr regA (DirAddr 2)              
 20: Load (ImmValue (-1)) regA                --Set thread_done flag for join(t2)
 21: WriteInstr regA (DirAddr 0)              
 22: Jump (Abs 55)                            
 23: TestAndSet (DirAddr 2)                   --Try to get lock(l2) // Start of thread t3
 24: Receive regA                             
 25: Compute Equal regA reg0 regA             
 26: Branch regA (Abs 23)                     
 27: TestAndSet (DirAddr 1)                   --Try to get lock(l1)
 28: Receive regA                             
 29: Compute Equal regA reg0 regA             
 30: Branch regA (Abs 27)                     
 31: Load (ImmValue 3) regA                   --Print
 32: WriteInstr regA numberIO                 
 33: Load (ImmValue 0) regA                   --Release lock(l1)
 34: WriteInstr regA (DirAddr 1)              
 35: Load (ImmValue 0) regA                   --Release lock(l2)
 36: WriteInstr regA (DirAddr 2)              
 37: Load (ImmValue (-1)) regA                --Set thread_done flag for join(t3)
 38: WriteInstr regA (DirAddr 3)              
 39: Jump (Abs 55)                            
 40: Load (ImmValue 1) regA                   --Get thread memory location for thread 1
 41: Compute NEq regA regSprID regA           
 42: Branch regA (Rel 3)                      
 43: Load (ImmValue 0) regA                   
 44: Jump (Abs 50)                            
 45: Load (ImmValue 2) regA                   --Get thread memory location for thread 2
 46: Compute NEq regA regSprID regA           
 47: Branch regA (Rel 3)                      
 48: Load (ImmValue 3) regA                   
 49: Jump (Abs 50)                            
 50: ReadInstr (IndAddr regA)                 --Thread pool idle loop
 51: Receive regB                             
 52: Compute Equal regB reg0 regC             
 53: Branch regC (Rel (-3))                   
 54: Jump (Ind regB)                          
 55: EndProg                                  
 56: Load (ImmValue 0) regA                   --Error Handling
 57: Compute NEq regF regA regB               
 58: Branch regB (Rel 51)                     
 59: Load (ImmValue 69) regA                  --Print error DivByZero
 60: WriteInstr regA charIO                   
 61: Load (ImmValue 114) regA                 
 62: WriteInstr regA charIO                   
 63: Load (ImmValue 114) regA                 
 64: WriteInstr regA charIO                   
 65: Load (ImmValue 111) regA                 
 66: WriteInstr regA charIO                   
 67: Load (ImmValue 114) regA                 
 68: WriteInstr regA charIO                   
 69: Load (ImmValue 58) regA                  
 70: WriteInstr regA charIO                   
 71: Load (ImmValue 32) regA                  
 72: WriteInstr regA charIO                   
 73: Load (ImmValue 67) regA                  
 74: WriteInstr regA charIO                   
 75: Load (ImmValue 97) regA                  
 76: WriteInstr regA charIO                   
 77: Load (ImmValue 110) regA                 
 78: WriteInstr regA charIO                   
 79: Load (ImmValue 110) regA                 
 80: WriteInstr regA charIO                   
 81: Load (ImmValue 111) regA                 
 82: WriteInstr regA charIO                   
 83: Load (ImmValue 116) regA                 
 84: WriteInstr regA charIO                   
 85: Load (ImmValue 32) regA                  
 86: WriteInstr regA charIO                   
 87: Load (ImmValue 100) regA                 
 88: WriteInstr regA charIO                   
 89: Load (ImmValue 105) regA                 
 90: WriteInstr regA charIO                   
 91: Load (ImmValue 118) regA                 
 92: WriteInstr regA charIO                   
 93: Load (ImmValue 105) regA                 
 94: WriteInstr regA charIO                   
 95: Load (ImmValue 100) regA                 
 96: WriteInstr regA charIO                   
 97: Load (ImmValue 101) regA                 
 98: WriteInstr regA charIO                   
 99: Load (ImmValue 32) regA                  
100: WriteInstr regA charIO                   
101: Load (ImmValue 98) regA                  
102: WriteInstr regA charIO                   
103: Load (ImmValue 121) regA                 
104: WriteInstr regA charIO                   
105: Load (ImmValue 32) regA                  
106: WriteInstr regA charIO                   
107: Load (ImmValue 48) regA                  
108: WriteInstr regA charIO                   
109: Load (ImmValue 1) regA                   
110: Compute NEq regF regA regB               
111: Branch regB (Rel 113)                    
112: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
113: WriteInstr regA charIO                   
114: Load (ImmValue 114) regA                 
115: WriteInstr regA charIO                   
116: Load (ImmValue 114) regA                 
117: WriteInstr regA charIO                   
118: Load (ImmValue 111) regA                 
119: WriteInstr regA charIO                   
120: Load (ImmValue 114) regA                 
121: WriteInstr regA charIO                   
122: Load (ImmValue 58) regA                  
123: WriteInstr regA charIO                   
124: Load (ImmValue 32) regA                  
125: WriteInstr regA charIO                   
126: Load (ImmValue 65) regA                  
127: WriteInstr regA charIO                   
128: Load (ImmValue 116) regA                 
129: WriteInstr regA charIO                   
130: Load (ImmValue 116) regA                 
131: WriteInstr regA charIO                   
132: Load (ImmValue 101) regA                 
133: WriteInstr regA charIO                   
134: Load (ImmValue 109) regA                 
135: WriteInstr regA charIO                   
136: Load (ImmValue 112) regA                 
137: WriteInstr regA charIO                   
138: Load (ImmValue 116) regA                 
139: WriteInstr regA charIO                   
140: Load (ImmValue 105) regA                 
141: WriteInstr regA charIO                   
142: Load (ImmValue 110) regA                 
143: WriteInstr regA charIO                   
144: Load (ImmValue 103) regA                 
145: WriteInstr regA charIO                   
146: Load (ImmValue 32) regA                  
147: WriteInstr regA charIO                   
148: Load (ImmValue 116) regA                 
149: WriteInstr regA charIO                   
150: Load (ImmValue 111) regA                 
151: WriteInstr regA charIO                   
152: Load (ImmValue 32) regA                  
153: WriteInstr regA charIO                   
154: Load (ImmValue 97) regA                  
155: WriteInstr regA charIO                   
156: Load (ImmValue 99) regA                  
157: WriteInstr regA charIO                   
158: Load (ImmValue 99) regA                  
159: WriteInstr regA charIO                   
160: Load (ImmValue 101) regA                 
161: WriteInstr regA charIO                   
162: Load (ImmValue 115) regA                 
163: WriteInstr regA charIO                   
164: Load (ImmValue 115) regA                 
165: WriteInstr regA charIO                   
166: Load (ImmValue 32) regA                  
167: WriteInstr regA charIO                   
168: Load (ImmValue 111) regA                 
169: WriteInstr regA charIO                   
170: Load (ImmValue 117) regA                 
171: WriteInstr regA charIO                   
172: Load (ImmValue 116) regA                 
173: WriteInstr regA charIO                   
174: Load (ImmValue 32) regA                  
175: WriteInstr regA charIO                   
176: Load (ImmValue 111) regA                 
177: WriteInstr regA charIO                   
178: Load (ImmValue 102) regA                 
179: WriteInstr regA charIO                   
180: Load (ImmValue 32) regA                  
181: WriteInstr regA charIO                   
182: Load (ImmValue 98) regA                  
183: WriteInstr regA charIO                   
184: Load (ImmValue 111) regA                 
185: WriteInstr regA charIO                   
186: Load (ImmValue 117) regA                 
187: WriteInstr regA charIO                   
188: Load (ImmValue 110) regA                 
189: WriteInstr regA charIO                   
190: Load (ImmValue 100) regA                 
191: WriteInstr regA charIO                   
192: Load (ImmValue 115) regA                 
193: WriteInstr regA charIO                   
194: Load (ImmValue 32) regA                  
195: WriteInstr regA charIO                   
196: Load (ImmValue 105) regA                 
197: WriteInstr regA charIO                   
198: Load (ImmValue 110) regA                 
199: WriteInstr regA charIO                   
200: Load (ImmValue 100) regA                 
201: WriteInstr regA charIO                   
202: Load (ImmValue 101) regA                 
203: WriteInstr regA charIO                   
204: Load (ImmValue 120) regA                 
205: WriteInstr regA charIO                   
206: Load (ImmValue 32) regA                  
207: WriteInstr regA charIO                   
208: Load (ImmValue 111) regA                 
209: WriteInstr regA charIO                   
210: Load (ImmValue 102) regA                 
211: WriteInstr regA charIO                   
212: Load (ImmValue 32) regA                  
213: WriteInstr regA charIO                   
214: Load (ImmValue 97) regA                  
215: WriteInstr regA charIO                   
216: Load (ImmValue 114) regA                 
217: WriteInstr regA charIO                   
218: Load (ImmValue 114) regA                 
219: WriteInstr regA charIO                   
220: Load (ImmValue 97) regA                  
221: WriteInstr regA charIO                   
222: Load (ImmValue 121) regA                 
223: WriteInstr regA charIO                   
224: EndProg     
``` 