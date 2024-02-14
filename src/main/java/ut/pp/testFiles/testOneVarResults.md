### Output:
```
Sprockell 0 says 10
Sprockell 0 says 205
Sprockell 0 says 12
Sprockell 0 says 206
Sprockell 0 says 14
Sprockell 0 says 207
Sprockell 0 says 16
Sprockell 0 says 108
Sprockell 0 says 18
Sprockell 0 says 109
Sprockell 0 says 1000
```

### Spril instructions:
```
  0: Load (ImmValue 5) regA                   --Assignment to a
  1: Store regA (DirAddr 0)                   
  2: Load (ImmValue 10) regB                  --While condition check
  3: Load (DirAddr 0) regA                    --(Loading var a)
  4: Compute Lt regA regB regA                
  5: Compute Equal regA reg0 regA             
  6: Branch regA (Abs 36)                     
  7: Load (ImmValue 2) regB                   --Print // While body
  8: Load (DirAddr 0) regA                    --(Loading var a)
  9: Compute Mul regA regB regA               
 10: WriteInstr regA numberIO                 
 11: Load (ImmValue 8) regB                   --Check if condition
 12: Load (DirAddr 0) regA                    --(Loading var a)
 13: Compute Equal regA regB regA             
 14: Compute Equal regA reg0 regA             
 15: Branch regA (Abs 19)                     
 16: Load (ImmValue 108) regA                 --Print // If body
 17: WriteInstr regA numberIO                 
 18: Jump (Abs 31)                            
 19: Load (ImmValue 9) regB                   --Check if condition
 20: Load (DirAddr 0) regA                    --(Loading var a)
 21: Compute Equal regA regB regA             
 22: Compute Equal regA reg0 regA             
 23: Branch regA (Abs 27)                     
 24: Load (ImmValue 109) regA                 --Print // If body
 25: WriteInstr regA numberIO                 
 26: Jump (Abs 31)                            
 27: Load (DirAddr 0) regB                    --(Loading var a) // Print // Else body
 28: Load (ImmValue 200) regA                 
 29: Compute Add regA regB regA               
 30: WriteInstr regA numberIO                 
 31: Load (ImmValue 1) regB                   --Assignment to a
 32: Load (DirAddr 0) regA                    --(Loading var a)
 33: Compute Add regA regB regA               
 34: Store regA (DirAddr 0)                   
 35: Jump (Abs 2)                             
 36: Load (ImmValue 1000) regA                --Print
 37: WriteInstr regA numberIO                 
 38: EndProg                                  
 39: Load (ImmValue 0) regA                   --Error Handling
 40: Compute NEq regF regA regB               
 41: Branch regB (Rel 51)                     
 42: Load (ImmValue 69) regA                  --Print error DivByZero
 43: WriteInstr regA charIO                   
 44: Load (ImmValue 114) regA                 
 45: WriteInstr regA charIO                   
 46: Load (ImmValue 114) regA                 
 47: WriteInstr regA charIO                   
 48: Load (ImmValue 111) regA                 
 49: WriteInstr regA charIO                   
 50: Load (ImmValue 114) regA                 
 51: WriteInstr regA charIO                   
 52: Load (ImmValue 58) regA                  
 53: WriteInstr regA charIO                   
 54: Load (ImmValue 32) regA                  
 55: WriteInstr regA charIO                   
 56: Load (ImmValue 67) regA                  
 57: WriteInstr regA charIO                   
 58: Load (ImmValue 97) regA                  
 59: WriteInstr regA charIO                   
 60: Load (ImmValue 110) regA                 
 61: WriteInstr regA charIO                   
 62: Load (ImmValue 110) regA                 
 63: WriteInstr regA charIO                   
 64: Load (ImmValue 111) regA                 
 65: WriteInstr regA charIO                   
 66: Load (ImmValue 116) regA                 
 67: WriteInstr regA charIO                   
 68: Load (ImmValue 32) regA                  
 69: WriteInstr regA charIO                   
 70: Load (ImmValue 100) regA                 
 71: WriteInstr regA charIO                   
 72: Load (ImmValue 105) regA                 
 73: WriteInstr regA charIO                   
 74: Load (ImmValue 118) regA                 
 75: WriteInstr regA charIO                   
 76: Load (ImmValue 105) regA                 
 77: WriteInstr regA charIO                   
 78: Load (ImmValue 100) regA                 
 79: WriteInstr regA charIO                   
 80: Load (ImmValue 101) regA                 
 81: WriteInstr regA charIO                   
 82: Load (ImmValue 32) regA                  
 83: WriteInstr regA charIO                   
 84: Load (ImmValue 98) regA                  
 85: WriteInstr regA charIO                   
 86: Load (ImmValue 121) regA                 
 87: WriteInstr regA charIO                   
 88: Load (ImmValue 32) regA                  
 89: WriteInstr regA charIO                   
 90: Load (ImmValue 48) regA                  
 91: WriteInstr regA charIO                   
 92: Load (ImmValue 1) regA                   
 93: Compute NEq regF regA regB               
 94: Branch regB (Rel 113)                    
 95: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
 96: WriteInstr regA charIO                   
 97: Load (ImmValue 114) regA                 
 98: WriteInstr regA charIO                   
 99: Load (ImmValue 114) regA                 
100: WriteInstr regA charIO                   
101: Load (ImmValue 111) regA                 
102: WriteInstr regA charIO                   
103: Load (ImmValue 114) regA                 
104: WriteInstr regA charIO                   
105: Load (ImmValue 58) regA                  
106: WriteInstr regA charIO                   
107: Load (ImmValue 32) regA                  
108: WriteInstr regA charIO                   
109: Load (ImmValue 65) regA                  
110: WriteInstr regA charIO                   
111: Load (ImmValue 116) regA                 
112: WriteInstr regA charIO                   
113: Load (ImmValue 116) regA                 
114: WriteInstr regA charIO                   
115: Load (ImmValue 101) regA                 
116: WriteInstr regA charIO                   
117: Load (ImmValue 109) regA                 
118: WriteInstr regA charIO                   
119: Load (ImmValue 112) regA                 
120: WriteInstr regA charIO                   
121: Load (ImmValue 116) regA                 
122: WriteInstr regA charIO                   
123: Load (ImmValue 105) regA                 
124: WriteInstr regA charIO                   
125: Load (ImmValue 110) regA                 
126: WriteInstr regA charIO                   
127: Load (ImmValue 103) regA                 
128: WriteInstr regA charIO                   
129: Load (ImmValue 32) regA                  
130: WriteInstr regA charIO                   
131: Load (ImmValue 116) regA                 
132: WriteInstr regA charIO                   
133: Load (ImmValue 111) regA                 
134: WriteInstr regA charIO                   
135: Load (ImmValue 32) regA                  
136: WriteInstr regA charIO                   
137: Load (ImmValue 97) regA                  
138: WriteInstr regA charIO                   
139: Load (ImmValue 99) regA                  
140: WriteInstr regA charIO                   
141: Load (ImmValue 99) regA                  
142: WriteInstr regA charIO                   
143: Load (ImmValue 101) regA                 
144: WriteInstr regA charIO                   
145: Load (ImmValue 115) regA                 
146: WriteInstr regA charIO                   
147: Load (ImmValue 115) regA                 
148: WriteInstr regA charIO                   
149: Load (ImmValue 32) regA                  
150: WriteInstr regA charIO                   
151: Load (ImmValue 111) regA                 
152: WriteInstr regA charIO                   
153: Load (ImmValue 117) regA                 
154: WriteInstr regA charIO                   
155: Load (ImmValue 116) regA                 
156: WriteInstr regA charIO                   
157: Load (ImmValue 32) regA                  
158: WriteInstr regA charIO                   
159: Load (ImmValue 111) regA                 
160: WriteInstr regA charIO                   
161: Load (ImmValue 102) regA                 
162: WriteInstr regA charIO                   
163: Load (ImmValue 32) regA                  
164: WriteInstr regA charIO                   
165: Load (ImmValue 98) regA                  
166: WriteInstr regA charIO                   
167: Load (ImmValue 111) regA                 
168: WriteInstr regA charIO                   
169: Load (ImmValue 117) regA                 
170: WriteInstr regA charIO                   
171: Load (ImmValue 110) regA                 
172: WriteInstr regA charIO                   
173: Load (ImmValue 100) regA                 
174: WriteInstr regA charIO                   
175: Load (ImmValue 115) regA                 
176: WriteInstr regA charIO                   
177: Load (ImmValue 32) regA                  
178: WriteInstr regA charIO                   
179: Load (ImmValue 105) regA                 
180: WriteInstr regA charIO                   
181: Load (ImmValue 110) regA                 
182: WriteInstr regA charIO                   
183: Load (ImmValue 100) regA                 
184: WriteInstr regA charIO                   
185: Load (ImmValue 101) regA                 
186: WriteInstr regA charIO                   
187: Load (ImmValue 120) regA                 
188: WriteInstr regA charIO                   
189: Load (ImmValue 32) regA                  
190: WriteInstr regA charIO                   
191: Load (ImmValue 111) regA                 
192: WriteInstr regA charIO                   
193: Load (ImmValue 102) regA                 
194: WriteInstr regA charIO                   
195: Load (ImmValue 32) regA                  
196: WriteInstr regA charIO                   
197: Load (ImmValue 97) regA                  
198: WriteInstr regA charIO                   
199: Load (ImmValue 114) regA                 
200: WriteInstr regA charIO                   
201: Load (ImmValue 114) regA                 
202: WriteInstr regA charIO                   
203: Load (ImmValue 97) regA                  
204: WriteInstr regA charIO                   
205: Load (ImmValue 121) regA                 
206: WriteInstr regA charIO                   
207: EndProg     
``` 