We also ran this file with if (sum > 10) and then it prints 6, 10, 13

### Output:
```
Sprockell 0 says 6
Sprockell 0 says 10
Sprockell 0 says 13
Sprockell 0 says 15
Sprockell 0 says 16
Sprockell 0 says 16
Sprockell 0 says 15
Sprockell 0 says 13
```

### Spril instructions:
```
  0: Load (ImmValue 5) regA                   --Assignment to x
  1: Store regA (DirAddr 0)                   
  2: Load (ImmValue 0) regA                   --Assignment to i
  3: Store regA (DirAddr 1)                   
  4: Load (ImmValue 0) regA                   --Assignment to sum
  5: Store regA (DirAddr 2)                   
  6: Load (ImmValue 1) regA                   --Assignment to b
  7: Store regA (DirAddr 3)                   
  8: Load (DirAddr 3) regA                    --(Loading var b) // While condition check
  9: Push regA                                
 10: Load (ImmValue 8) regB                   
 11: Load (DirAddr 1) regA                    --(Loading var i)
 12: Compute Lt regA regB regA                
 13: Pop regB                                 
 14: Compute And regA regB regA               
 15: Compute Equal regA reg0 regA             
 16: Branch regA (Abs 41)                     
 17: Load (DirAddr 0) regB                    --(Loading var x) // Assignment to sum // While body
 18: Load (DirAddr 2) regA                    --(Loading var sum)
 19: Compute Add regA regB regA               
 20: Store regA (DirAddr 2)                   
 21: Load (ImmValue 1) regB                   --Assignment to x
 22: Load (DirAddr 0) regA                    --(Loading var x)
 23: Compute Sub regA regB regA               
 24: Store regA (DirAddr 0)                   
 25: Load (ImmValue 1) regB                   --Assignment to i
 26: Load (DirAddr 1) regA                    --(Loading var i)
 27: Compute Add regA regB regA               
 28: Store regA (DirAddr 1)                   
 29: Load (ImmValue 20) regB                  --Check if condition
 30: Load (DirAddr 2) regA                    --(Loading var sum)
 31: Compute Gt regA regB regA                
 32: Compute Equal regA reg0 regA             
 33: Branch regA (Abs 36)                     
 34: Load (ImmValue 0) regA                   --Assignment to b // If body
 35: Store regA (DirAddr 3)                   
 36: Load (ImmValue 1) regB                   --Print
 37: Load (DirAddr 2) regA                    --(Loading var sum)
 38: Compute Add regA regB regA               
 39: WriteInstr regA numberIO                 
 40: Jump (Abs 8)                             
 41: EndProg                                  
 42: Load (ImmValue 0) regA                   --Error Handling
 43: Compute NEq regF regA regB               
 44: Branch regB (Rel 51)                     
 45: Load (ImmValue 69) regA                  --Print error DivByZero
 46: WriteInstr regA charIO                   
 47: Load (ImmValue 114) regA                 
 48: WriteInstr regA charIO                   
 49: Load (ImmValue 114) regA                 
 50: WriteInstr regA charIO                   
 51: Load (ImmValue 111) regA                 
 52: WriteInstr regA charIO                   
 53: Load (ImmValue 114) regA                 
 54: WriteInstr regA charIO                   
 55: Load (ImmValue 58) regA                  
 56: WriteInstr regA charIO                   
 57: Load (ImmValue 32) regA                  
 58: WriteInstr regA charIO                   
 59: Load (ImmValue 67) regA                  
 60: WriteInstr regA charIO                   
 61: Load (ImmValue 97) regA                  
 62: WriteInstr regA charIO                   
 63: Load (ImmValue 110) regA                 
 64: WriteInstr regA charIO                   
 65: Load (ImmValue 110) regA                 
 66: WriteInstr regA charIO                   
 67: Load (ImmValue 111) regA                 
 68: WriteInstr regA charIO                   
 69: Load (ImmValue 116) regA                 
 70: WriteInstr regA charIO                   
 71: Load (ImmValue 32) regA                  
 72: WriteInstr regA charIO                   
 73: Load (ImmValue 100) regA                 
 74: WriteInstr regA charIO                   
 75: Load (ImmValue 105) regA                 
 76: WriteInstr regA charIO                   
 77: Load (ImmValue 118) regA                 
 78: WriteInstr regA charIO                   
 79: Load (ImmValue 105) regA                 
 80: WriteInstr regA charIO                   
 81: Load (ImmValue 100) regA                 
 82: WriteInstr regA charIO                   
 83: Load (ImmValue 101) regA                 
 84: WriteInstr regA charIO                   
 85: Load (ImmValue 32) regA                  
 86: WriteInstr regA charIO                   
 87: Load (ImmValue 98) regA                  
 88: WriteInstr regA charIO                   
 89: Load (ImmValue 121) regA                 
 90: WriteInstr regA charIO                   
 91: Load (ImmValue 32) regA                  
 92: WriteInstr regA charIO                   
 93: Load (ImmValue 48) regA                  
 94: WriteInstr regA charIO                   
 95: Load (ImmValue 1) regA                   
 96: Compute NEq regF regA regB               
 97: Branch regB (Rel 113)                    
 98: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
 99: WriteInstr regA charIO                   
100: Load (ImmValue 114) regA                 
101: WriteInstr regA charIO                   
102: Load (ImmValue 114) regA                 
103: WriteInstr regA charIO                   
104: Load (ImmValue 111) regA                 
105: WriteInstr regA charIO                   
106: Load (ImmValue 114) regA                 
107: WriteInstr regA charIO                   
108: Load (ImmValue 58) regA                  
109: WriteInstr regA charIO                   
110: Load (ImmValue 32) regA                  
111: WriteInstr regA charIO                   
112: Load (ImmValue 65) regA                  
113: WriteInstr regA charIO                   
114: Load (ImmValue 116) regA                 
115: WriteInstr regA charIO                   
116: Load (ImmValue 116) regA                 
117: WriteInstr regA charIO                   
118: Load (ImmValue 101) regA                 
119: WriteInstr regA charIO                   
120: Load (ImmValue 109) regA                 
121: WriteInstr regA charIO                   
122: Load (ImmValue 112) regA                 
123: WriteInstr regA charIO                   
124: Load (ImmValue 116) regA                 
125: WriteInstr regA charIO                   
126: Load (ImmValue 105) regA                 
127: WriteInstr regA charIO                   
128: Load (ImmValue 110) regA                 
129: WriteInstr regA charIO                   
130: Load (ImmValue 103) regA                 
131: WriteInstr regA charIO                   
132: Load (ImmValue 32) regA                  
133: WriteInstr regA charIO                   
134: Load (ImmValue 116) regA                 
135: WriteInstr regA charIO                   
136: Load (ImmValue 111) regA                 
137: WriteInstr regA charIO                   
138: Load (ImmValue 32) regA                  
139: WriteInstr regA charIO                   
140: Load (ImmValue 97) regA                  
141: WriteInstr regA charIO                   
142: Load (ImmValue 99) regA                  
143: WriteInstr regA charIO                   
144: Load (ImmValue 99) regA                  
145: WriteInstr regA charIO                   
146: Load (ImmValue 101) regA                 
147: WriteInstr regA charIO                   
148: Load (ImmValue 115) regA                 
149: WriteInstr regA charIO                   
150: Load (ImmValue 115) regA                 
151: WriteInstr regA charIO                   
152: Load (ImmValue 32) regA                  
153: WriteInstr regA charIO                   
154: Load (ImmValue 111) regA                 
155: WriteInstr regA charIO                   
156: Load (ImmValue 117) regA                 
157: WriteInstr regA charIO                   
158: Load (ImmValue 116) regA                 
159: WriteInstr regA charIO                   
160: Load (ImmValue 32) regA                  
161: WriteInstr regA charIO                   
162: Load (ImmValue 111) regA                 
163: WriteInstr regA charIO                   
164: Load (ImmValue 102) regA                 
165: WriteInstr regA charIO                   
166: Load (ImmValue 32) regA                  
167: WriteInstr regA charIO                   
168: Load (ImmValue 98) regA                  
169: WriteInstr regA charIO                   
170: Load (ImmValue 111) regA                 
171: WriteInstr regA charIO                   
172: Load (ImmValue 117) regA                 
173: WriteInstr regA charIO                   
174: Load (ImmValue 110) regA                 
175: WriteInstr regA charIO                   
176: Load (ImmValue 100) regA                 
177: WriteInstr regA charIO                   
178: Load (ImmValue 115) regA                 
179: WriteInstr regA charIO                   
180: Load (ImmValue 32) regA                  
181: WriteInstr regA charIO                   
182: Load (ImmValue 105) regA                 
183: WriteInstr regA charIO                   
184: Load (ImmValue 110) regA                 
185: WriteInstr regA charIO                   
186: Load (ImmValue 100) regA                 
187: WriteInstr regA charIO                   
188: Load (ImmValue 101) regA                 
189: WriteInstr regA charIO                   
190: Load (ImmValue 120) regA                 
191: WriteInstr regA charIO                   
192: Load (ImmValue 32) regA                  
193: WriteInstr regA charIO                   
194: Load (ImmValue 111) regA                 
195: WriteInstr regA charIO                   
196: Load (ImmValue 102) regA                 
197: WriteInstr regA charIO                   
198: Load (ImmValue 32) regA                  
199: WriteInstr regA charIO                   
200: Load (ImmValue 97) regA                  
201: WriteInstr regA charIO                   
202: Load (ImmValue 114) regA                 
203: WriteInstr regA charIO                   
204: Load (ImmValue 114) regA                 
205: WriteInstr regA charIO                   
206: Load (ImmValue 97) regA                  
207: WriteInstr regA charIO                   
208: Load (ImmValue 121) regA                 
209: WriteInstr regA charIO                   
210: EndProg               
``` 