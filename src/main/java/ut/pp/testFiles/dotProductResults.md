### Output:
```
Sprockell 0 says 3
```

### Spril instructions:
```
  0: Load (ImmValue 1) regA                   --Assignment of array to x
  1: Store regA (DirAddr 0)                   
  2: Load (ImmValue 3) regA                   
  3: Store regA (DirAddr 1)                   
  4: Load (ImmValue (-5)) regA                
  5: Store regA (DirAddr 2)                   
  6: Load (ImmValue 4) regA                   --Assignment of array to y
  7: Store regA (DirAddr 3)                   
  8: Load (ImmValue (-2)) regA                
  9: Store regA (DirAddr 4)                   
 10: Load (ImmValue (-1)) regA                
 11: Store regA (DirAddr 5)                   
 12: Load (ImmValue 0) regA                   --Assignment to result
 13: Store regA (DirAddr 6)                   
 14: Load (ImmValue 0) regA                   --Assignment to i
 15: Store regA (DirAddr 7)                   
 16: Load (ImmValue 2) regB                   --While condition check
 17: Load (DirAddr 7) regA                    --(Loading var i)
 18: Compute LtE regA regB regA               
 19: Compute Equal regA reg0 regA             
 20: Branch regA (Abs 52)                     
 21: Load (DirAddr 7) regA                    --(Loading var i) // Calculating offset for reading from variable y // Assignment to result // While body
 22: Compute Lt regA reg0 regB                
 23: Load (ImmValue 2) regC                   
 24: Compute Gt regA regC regC                
 25: Compute Or regB regC regB                
 26: Load (ImmValue 1) regF                   
 27: Branch regB (Abs 55)                     --Branch to error
 28: Load (ImmValue 3) regB                   
 29: Compute Add regA regB regA               
 30: Load (IndAddr regA) regA                 --(Loading array index y at calculated offset)
 31: Push regA                                
 32: Load (DirAddr 7) regA                    --(Loading var i) // Calculating offset for reading from variable x
 33: Compute Lt regA reg0 regB                
 34: Load (ImmValue 2) regC                   
 35: Compute Gt regA regC regC                
 36: Compute Or regB regC regB                
 37: Load (ImmValue 1) regF                   
 38: Branch regB (Abs 55)                     --Branch to error
 39: Load (ImmValue 0) regB                   
 40: Compute Add regA regB regA               
 41: Load (IndAddr regA) regA                 --(Loading array index x at calculated offset)
 42: Pop regB                                 
 43: Compute Mul regA regB regB               
 44: Load (DirAddr 6) regA                    --(Loading var result)
 45: Compute Add regA regB regA               
 46: Store regA (DirAddr 6)                   
 47: Load (ImmValue 1) regB                   --Assignment to i
 48: Load (DirAddr 7) regA                    --(Loading var i)
 49: Compute Add regA regB regA               
 50: Store regA (DirAddr 7)                   
 51: Jump (Abs 16)                            
 52: Load (DirAddr 6) regA                    --(Loading var result) // Print
 53: WriteInstr regA numberIO                 
 54: EndProg                                  
 55: Load (ImmValue 0) regA                   --Error Handling
 56: Compute NEq regF regA regB               
 57: Branch regB (Rel 51)                     
 58: Load (ImmValue 69) regA                  --Print error DivByZero
 59: WriteInstr regA charIO                   
 60: Load (ImmValue 114) regA                 
 61: WriteInstr regA charIO                   
 62: Load (ImmValue 114) regA                 
 63: WriteInstr regA charIO                   
 64: Load (ImmValue 111) regA                 
 65: WriteInstr regA charIO                   
 66: Load (ImmValue 114) regA                 
 67: WriteInstr regA charIO                   
 68: Load (ImmValue 58) regA                  
 69: WriteInstr regA charIO                   
 70: Load (ImmValue 32) regA                  
 71: WriteInstr regA charIO                   
 72: Load (ImmValue 67) regA                  
 73: WriteInstr regA charIO                   
 74: Load (ImmValue 97) regA                  
 75: WriteInstr regA charIO                   
 76: Load (ImmValue 110) regA                 
 77: WriteInstr regA charIO                   
 78: Load (ImmValue 110) regA                 
 79: WriteInstr regA charIO                   
 80: Load (ImmValue 111) regA                 
 81: WriteInstr regA charIO                   
 82: Load (ImmValue 116) regA                 
 83: WriteInstr regA charIO                   
 84: Load (ImmValue 32) regA                  
 85: WriteInstr regA charIO                   
 86: Load (ImmValue 100) regA                 
 87: WriteInstr regA charIO                   
 88: Load (ImmValue 105) regA                 
 89: WriteInstr regA charIO                   
 90: Load (ImmValue 118) regA                 
 91: WriteInstr regA charIO                   
 92: Load (ImmValue 105) regA                 
 93: WriteInstr regA charIO                   
 94: Load (ImmValue 100) regA                 
 95: WriteInstr regA charIO                   
 96: Load (ImmValue 101) regA                 
 97: WriteInstr regA charIO                   
 98: Load (ImmValue 32) regA                  
 99: WriteInstr regA charIO                   
100: Load (ImmValue 98) regA                  
101: WriteInstr regA charIO                   
102: Load (ImmValue 121) regA                 
103: WriteInstr regA charIO                   
104: Load (ImmValue 32) regA                  
105: WriteInstr regA charIO                   
106: Load (ImmValue 48) regA                  
107: WriteInstr regA charIO                   
108: Load (ImmValue 1) regA                   
109: Compute NEq regF regA regB               
110: Branch regB (Rel 113)                    
111: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
112: WriteInstr regA charIO                   
113: Load (ImmValue 114) regA                 
114: WriteInstr regA charIO                   
115: Load (ImmValue 114) regA                 
116: WriteInstr regA charIO                   
117: Load (ImmValue 111) regA                 
118: WriteInstr regA charIO                   
119: Load (ImmValue 114) regA                 
120: WriteInstr regA charIO                   
121: Load (ImmValue 58) regA                  
122: WriteInstr regA charIO                   
123: Load (ImmValue 32) regA                  
124: WriteInstr regA charIO                   
125: Load (ImmValue 65) regA                  
126: WriteInstr regA charIO                   
127: Load (ImmValue 116) regA                 
128: WriteInstr regA charIO                   
129: Load (ImmValue 116) regA                 
130: WriteInstr regA charIO                   
131: Load (ImmValue 101) regA                 
132: WriteInstr regA charIO                   
133: Load (ImmValue 109) regA                 
134: WriteInstr regA charIO                   
135: Load (ImmValue 112) regA                 
136: WriteInstr regA charIO                   
137: Load (ImmValue 116) regA                 
138: WriteInstr regA charIO                   
139: Load (ImmValue 105) regA                 
140: WriteInstr regA charIO                   
141: Load (ImmValue 110) regA                 
142: WriteInstr regA charIO                   
143: Load (ImmValue 103) regA                 
144: WriteInstr regA charIO                   
145: Load (ImmValue 32) regA                  
146: WriteInstr regA charIO                   
147: Load (ImmValue 116) regA                 
148: WriteInstr regA charIO                   
149: Load (ImmValue 111) regA                 
150: WriteInstr regA charIO                   
151: Load (ImmValue 32) regA                  
152: WriteInstr regA charIO                   
153: Load (ImmValue 97) regA                  
154: WriteInstr regA charIO                   
155: Load (ImmValue 99) regA                  
156: WriteInstr regA charIO                   
157: Load (ImmValue 99) regA                  
158: WriteInstr regA charIO                   
159: Load (ImmValue 101) regA                 
160: WriteInstr regA charIO                   
161: Load (ImmValue 115) regA                 
162: WriteInstr regA charIO                   
163: Load (ImmValue 115) regA                 
164: WriteInstr regA charIO                   
165: Load (ImmValue 32) regA                  
166: WriteInstr regA charIO                   
167: Load (ImmValue 111) regA                 
168: WriteInstr regA charIO                   
169: Load (ImmValue 117) regA                 
170: WriteInstr regA charIO                   
171: Load (ImmValue 116) regA                 
172: WriteInstr regA charIO                   
173: Load (ImmValue 32) regA                  
174: WriteInstr regA charIO                   
175: Load (ImmValue 111) regA                 
176: WriteInstr regA charIO                   
177: Load (ImmValue 102) regA                 
178: WriteInstr regA charIO                   
179: Load (ImmValue 32) regA                  
180: WriteInstr regA charIO                   
181: Load (ImmValue 98) regA                  
182: WriteInstr regA charIO                   
183: Load (ImmValue 111) regA                 
184: WriteInstr regA charIO                   
185: Load (ImmValue 117) regA                 
186: WriteInstr regA charIO                   
187: Load (ImmValue 110) regA                 
188: WriteInstr regA charIO                   
189: Load (ImmValue 100) regA                 
190: WriteInstr regA charIO                   
191: Load (ImmValue 115) regA                 
192: WriteInstr regA charIO                   
193: Load (ImmValue 32) regA                  
194: WriteInstr regA charIO                   
195: Load (ImmValue 105) regA                 
196: WriteInstr regA charIO                   
197: Load (ImmValue 110) regA                 
198: WriteInstr regA charIO                   
199: Load (ImmValue 100) regA                 
200: WriteInstr regA charIO                   
201: Load (ImmValue 101) regA                 
202: WriteInstr regA charIO                   
203: Load (ImmValue 120) regA                 
204: WriteInstr regA charIO                   
205: Load (ImmValue 32) regA                  
206: WriteInstr regA charIO                   
207: Load (ImmValue 111) regA                 
208: WriteInstr regA charIO                   
209: Load (ImmValue 102) regA                 
210: WriteInstr regA charIO                   
211: Load (ImmValue 32) regA                  
212: WriteInstr regA charIO                   
213: Load (ImmValue 97) regA                  
214: WriteInstr regA charIO                   
215: Load (ImmValue 114) regA                 
216: WriteInstr regA charIO                   
217: Load (ImmValue 114) regA                 
218: WriteInstr regA charIO                   
219: Load (ImmValue 97) regA                  
220: WriteInstr regA charIO                   
221: Load (ImmValue 121) regA                 
222: WriteInstr regA charIO                   
223: EndProg                   
``` 