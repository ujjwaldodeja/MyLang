prints fibonacci sequence, with final result printed twice

### Output:
```
Sprockell 0 says 2
Sprockell 0 says 3
Sprockell 0 says 5
Sprockell 0 says 8
Sprockell 0 says 13
Sprockell 0 says 13
```

### Spril instructions:
```
  0: Load (ImmValue 1) regA                   --Assignment to x
  1: Store regA (DirAddr 0)                   
  2: Load (ImmValue 1) regA                   --Assignment to y
  3: Store regA (DirAddr 1)                   
  4: Store reg0 (DirAddr 2)                   --Declaration of z
  5: Load (ImmValue 6) regA                   --Assignment to n
  6: Store regA (DirAddr 3)                   
  7: Load (ImmValue 1) regB                   --While condition check
  8: Load (DirAddr 3) regA                    --(Loading var n)
  9: Compute Gt regA regB regA                
 10: Compute Equal regA reg0 regA             
 11: Branch regA (Abs 27)                     
 12: Load (DirAddr 1) regB                    --(Loading var y) // Assignment to z // While body
 13: Load (DirAddr 0) regA                    --(Loading var x)
 14: Compute Add regA regB regA               
 15: Store regA (DirAddr 2)                   
 16: Load (DirAddr 1) regA                    --(Loading var y) // Assignment to x
 17: Store regA (DirAddr 0)                   
 18: Load (DirAddr 2) regA                    --(Loading var z) // Assignment to y
 19: Store regA (DirAddr 1)                   
 20: Load (ImmValue 1) regB                   --Assignment to n
 21: Load (DirAddr 3) regA                    --(Loading var n)
 22: Compute Sub regA regB regA               
 23: Store regA (DirAddr 3)                   
 24: Load (DirAddr 2) regA                    --(Loading var z) // Print
 25: WriteInstr regA numberIO                 
 26: Jump (Abs 7)                             
 27: Load (DirAddr 2) regA                    --(Loading var z) // Print
 28: WriteInstr regA numberIO                 
 29: EndProg                                  
 30: Load (ImmValue 0) regA                   --Error Handling
 31: Compute NEq regF regA regB               
 32: Branch regB (Rel 51)                     
 33: Load (ImmValue 69) regA                  --Print error DivByZero
 34: WriteInstr regA charIO                   
 35: Load (ImmValue 114) regA                 
 36: WriteInstr regA charIO                   
 37: Load (ImmValue 114) regA                 
 38: WriteInstr regA charIO                   
 39: Load (ImmValue 111) regA                 
 40: WriteInstr regA charIO                   
 41: Load (ImmValue 114) regA                 
 42: WriteInstr regA charIO                   
 43: Load (ImmValue 58) regA                  
 44: WriteInstr regA charIO                   
 45: Load (ImmValue 32) regA                  
 46: WriteInstr regA charIO                   
 47: Load (ImmValue 67) regA                  
 48: WriteInstr regA charIO                   
 49: Load (ImmValue 97) regA                  
 50: WriteInstr regA charIO                   
 51: Load (ImmValue 110) regA                 
 52: WriteInstr regA charIO                   
 53: Load (ImmValue 110) regA                 
 54: WriteInstr regA charIO                   
 55: Load (ImmValue 111) regA                 
 56: WriteInstr regA charIO                   
 57: Load (ImmValue 116) regA                 
 58: WriteInstr regA charIO                   
 59: Load (ImmValue 32) regA                  
 60: WriteInstr regA charIO                   
 61: Load (ImmValue 100) regA                 
 62: WriteInstr regA charIO                   
 63: Load (ImmValue 105) regA                 
 64: WriteInstr regA charIO                   
 65: Load (ImmValue 118) regA                 
 66: WriteInstr regA charIO                   
 67: Load (ImmValue 105) regA                 
 68: WriteInstr regA charIO                   
 69: Load (ImmValue 100) regA                 
 70: WriteInstr regA charIO                   
 71: Load (ImmValue 101) regA                 
 72: WriteInstr regA charIO                   
 73: Load (ImmValue 32) regA                  
 74: WriteInstr regA charIO                   
 75: Load (ImmValue 98) regA                  
 76: WriteInstr regA charIO                   
 77: Load (ImmValue 121) regA                 
 78: WriteInstr regA charIO                   
 79: Load (ImmValue 32) regA                  
 80: WriteInstr regA charIO                   
 81: Load (ImmValue 48) regA                  
 82: WriteInstr regA charIO                   
 83: Load (ImmValue 1) regA                   
 84: Compute NEq regF regA regB               
 85: Branch regB (Rel 113)                    
 86: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
 87: WriteInstr regA charIO                   
 88: Load (ImmValue 114) regA                 
 89: WriteInstr regA charIO                   
 90: Load (ImmValue 114) regA                 
 91: WriteInstr regA charIO                   
 92: Load (ImmValue 111) regA                 
 93: WriteInstr regA charIO                   
 94: Load (ImmValue 114) regA                 
 95: WriteInstr regA charIO                   
 96: Load (ImmValue 58) regA                  
 97: WriteInstr regA charIO                   
 98: Load (ImmValue 32) regA                  
 99: WriteInstr regA charIO                   
100: Load (ImmValue 65) regA                  
101: WriteInstr regA charIO                   
102: Load (ImmValue 116) regA                 
103: WriteInstr regA charIO                   
104: Load (ImmValue 116) regA                 
105: WriteInstr regA charIO                   
106: Load (ImmValue 101) regA                 
107: WriteInstr regA charIO                   
108: Load (ImmValue 109) regA                 
109: WriteInstr regA charIO                   
110: Load (ImmValue 112) regA                 
111: WriteInstr regA charIO                   
112: Load (ImmValue 116) regA                 
113: WriteInstr regA charIO                   
114: Load (ImmValue 105) regA                 
115: WriteInstr regA charIO                   
116: Load (ImmValue 110) regA                 
117: WriteInstr regA charIO                   
118: Load (ImmValue 103) regA                 
119: WriteInstr regA charIO                   
120: Load (ImmValue 32) regA                  
121: WriteInstr regA charIO                   
122: Load (ImmValue 116) regA                 
123: WriteInstr regA charIO                   
124: Load (ImmValue 111) regA                 
125: WriteInstr regA charIO                   
126: Load (ImmValue 32) regA                  
127: WriteInstr regA charIO                   
128: Load (ImmValue 97) regA                  
129: WriteInstr regA charIO                   
130: Load (ImmValue 99) regA                  
131: WriteInstr regA charIO                   
132: Load (ImmValue 99) regA                  
133: WriteInstr regA charIO                   
134: Load (ImmValue 101) regA                 
135: WriteInstr regA charIO                   
136: Load (ImmValue 115) regA                 
137: WriteInstr regA charIO                   
138: Load (ImmValue 115) regA                 
139: WriteInstr regA charIO                   
140: Load (ImmValue 32) regA                  
141: WriteInstr regA charIO                   
142: Load (ImmValue 111) regA                 
143: WriteInstr regA charIO                   
144: Load (ImmValue 117) regA                 
145: WriteInstr regA charIO                   
146: Load (ImmValue 116) regA                 
147: WriteInstr regA charIO                   
148: Load (ImmValue 32) regA                  
149: WriteInstr regA charIO                   
150: Load (ImmValue 111) regA                 
151: WriteInstr regA charIO                   
152: Load (ImmValue 102) regA                 
153: WriteInstr regA charIO                   
154: Load (ImmValue 32) regA                  
155: WriteInstr regA charIO                   
156: Load (ImmValue 98) regA                  
157: WriteInstr regA charIO                   
158: Load (ImmValue 111) regA                 
159: WriteInstr regA charIO                   
160: Load (ImmValue 117) regA                 
161: WriteInstr regA charIO                   
162: Load (ImmValue 110) regA                 
163: WriteInstr regA charIO                   
164: Load (ImmValue 100) regA                 
165: WriteInstr regA charIO                   
166: Load (ImmValue 115) regA                 
167: WriteInstr regA charIO                   
168: Load (ImmValue 32) regA                  
169: WriteInstr regA charIO                   
170: Load (ImmValue 105) regA                 
171: WriteInstr regA charIO                   
172: Load (ImmValue 110) regA                 
173: WriteInstr regA charIO                   
174: Load (ImmValue 100) regA                 
175: WriteInstr regA charIO                   
176: Load (ImmValue 101) regA                 
177: WriteInstr regA charIO                   
178: Load (ImmValue 120) regA                 
179: WriteInstr regA charIO                   
180: Load (ImmValue 32) regA                  
181: WriteInstr regA charIO                   
182: Load (ImmValue 111) regA                 
183: WriteInstr regA charIO                   
184: Load (ImmValue 102) regA                 
185: WriteInstr regA charIO                   
186: Load (ImmValue 32) regA                  
187: WriteInstr regA charIO                   
188: Load (ImmValue 97) regA                  
189: WriteInstr regA charIO                   
190: Load (ImmValue 114) regA                 
191: WriteInstr regA charIO                   
192: Load (ImmValue 114) regA                 
193: WriteInstr regA charIO                   
194: Load (ImmValue 97) regA                  
195: WriteInstr regA charIO                   
196: Load (ImmValue 121) regA                 
197: WriteInstr regA charIO                   
198: EndProg               
``` 