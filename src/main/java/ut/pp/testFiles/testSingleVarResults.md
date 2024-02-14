### Output:
```
Sprockell 0 says 3
Sprockell 0 says 5
Sprockell 0 says 6
Sprockell 0 says 7
Sprockell 0 says 8
Sprockell 0 says 9
Sprockell 0 says 10
Sprockell 0 says 11
Sprockell 0 says 12
Sprockell 0 says 13
Sprockell 0 says 14
Sprockell 0 says 15
Sprockell 0 says 16
Sprockell 0 says 17
Sprockell 0 says 18
Sprockell 0 says 19
Sprockell 0 says 20
```

### Spril instructions:
```
  0: Load (ImmValue 1) regA                   --Assignment to x
  1: Store regA (DirAddr 0)                   
  2: Load (ImmValue 20) regB                  --While condition check
  3: Load (DirAddr 0) regA                    --(Loading var x)
  4: Compute NEq regA regB regA               
  5: Compute Equal regA reg0 regA             
  6: Branch regA (Abs 24)                     
  7: Load (ImmValue 5) regB                   --Check if condition // While body
  8: Load (DirAddr 0) regA                    --(Loading var x)
  9: Compute Lt regA regB regA                
 10: Compute Equal regA reg0 regA             
 11: Branch regA (Abs 17)                     
 12: Load (ImmValue 2) regB                   --Assignment to x // If body
 13: Load (DirAddr 0) regA                    --(Loading var x)
 14: Compute Add regA regB regA               
 15: Store regA (DirAddr 0)                   
 16: Jump (Abs 21)                            
 17: Load (ImmValue 1) regB                   --Assignment to x // Else body
 18: Load (DirAddr 0) regA                    --(Loading var x)
 19: Compute Add regA regB regA               
 20: Store regA (DirAddr 0)                   
 21: Load (DirAddr 0) regA                    --(Loading var x) // Print
 22: WriteInstr regA numberIO                 
 23: Jump (Abs 2)                             
 24: EndProg                                  
 25: Load (ImmValue 0) regA                   --Error Handling
 26: Compute NEq regF regA regB               
 27: Branch regB (Rel 51)                     
 28: Load (ImmValue 69) regA                  --Print error DivByZero
 29: WriteInstr regA charIO                   
 30: Load (ImmValue 114) regA                 
 31: WriteInstr regA charIO                   
 32: Load (ImmValue 114) regA                 
 33: WriteInstr regA charIO                   
 34: Load (ImmValue 111) regA                 
 35: WriteInstr regA charIO                   
 36: Load (ImmValue 114) regA                 
 37: WriteInstr regA charIO                   
 38: Load (ImmValue 58) regA                  
 39: WriteInstr regA charIO                   
 40: Load (ImmValue 32) regA                  
 41: WriteInstr regA charIO                   
 42: Load (ImmValue 67) regA                  
 43: WriteInstr regA charIO                   
 44: Load (ImmValue 97) regA                  
 45: WriteInstr regA charIO                   
 46: Load (ImmValue 110) regA                 
 47: WriteInstr regA charIO                   
 48: Load (ImmValue 110) regA                 
 49: WriteInstr regA charIO                   
 50: Load (ImmValue 111) regA                 
 51: WriteInstr regA charIO                   
 52: Load (ImmValue 116) regA                 
 53: WriteInstr regA charIO                   
 54: Load (ImmValue 32) regA                  
 55: WriteInstr regA charIO                   
 56: Load (ImmValue 100) regA                 
 57: WriteInstr regA charIO                   
 58: Load (ImmValue 105) regA                 
 59: WriteInstr regA charIO                   
 60: Load (ImmValue 118) regA                 
 61: WriteInstr regA charIO                   
 62: Load (ImmValue 105) regA                 
 63: WriteInstr regA charIO                   
 64: Load (ImmValue 100) regA                 
 65: WriteInstr regA charIO                   
 66: Load (ImmValue 101) regA                 
 67: WriteInstr regA charIO                   
 68: Load (ImmValue 32) regA                  
 69: WriteInstr regA charIO                   
 70: Load (ImmValue 98) regA                  
 71: WriteInstr regA charIO                   
 72: Load (ImmValue 121) regA                 
 73: WriteInstr regA charIO                   
 74: Load (ImmValue 32) regA                  
 75: WriteInstr regA charIO                   
 76: Load (ImmValue 48) regA                  
 77: WriteInstr regA charIO                   
 78: Load (ImmValue 1) regA                   
 79: Compute NEq regF regA regB               
 80: Branch regB (Rel 113)                    
 81: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
 82: WriteInstr regA charIO                   
 83: Load (ImmValue 114) regA                 
 84: WriteInstr regA charIO                   
 85: Load (ImmValue 114) regA                 
 86: WriteInstr regA charIO                   
 87: Load (ImmValue 111) regA                 
 88: WriteInstr regA charIO                   
 89: Load (ImmValue 114) regA                 
 90: WriteInstr regA charIO                   
 91: Load (ImmValue 58) regA                  
 92: WriteInstr regA charIO                   
 93: Load (ImmValue 32) regA                  
 94: WriteInstr regA charIO                   
 95: Load (ImmValue 65) regA                  
 96: WriteInstr regA charIO                   
 97: Load (ImmValue 116) regA                 
 98: WriteInstr regA charIO                   
 99: Load (ImmValue 116) regA                 
100: WriteInstr regA charIO                   
101: Load (ImmValue 101) regA                 
102: WriteInstr regA charIO                   
103: Load (ImmValue 109) regA                 
104: WriteInstr regA charIO                   
105: Load (ImmValue 112) regA                 
106: WriteInstr regA charIO                   
107: Load (ImmValue 116) regA                 
108: WriteInstr regA charIO                   
109: Load (ImmValue 105) regA                 
110: WriteInstr regA charIO                   
111: Load (ImmValue 110) regA                 
112: WriteInstr regA charIO                   
113: Load (ImmValue 103) regA                 
114: WriteInstr regA charIO                   
115: Load (ImmValue 32) regA                  
116: WriteInstr regA charIO                   
117: Load (ImmValue 116) regA                 
118: WriteInstr regA charIO                   
119: Load (ImmValue 111) regA                 
120: WriteInstr regA charIO                   
121: Load (ImmValue 32) regA                  
122: WriteInstr regA charIO                   
123: Load (ImmValue 97) regA                  
124: WriteInstr regA charIO                   
125: Load (ImmValue 99) regA                  
126: WriteInstr regA charIO                   
127: Load (ImmValue 99) regA                  
128: WriteInstr regA charIO                   
129: Load (ImmValue 101) regA                 
130: WriteInstr regA charIO                   
131: Load (ImmValue 115) regA                 
132: WriteInstr regA charIO                   
133: Load (ImmValue 115) regA                 
134: WriteInstr regA charIO                   
135: Load (ImmValue 32) regA                  
136: WriteInstr regA charIO                   
137: Load (ImmValue 111) regA                 
138: WriteInstr regA charIO                   
139: Load (ImmValue 117) regA                 
140: WriteInstr regA charIO                   
141: Load (ImmValue 116) regA                 
142: WriteInstr regA charIO                   
143: Load (ImmValue 32) regA                  
144: WriteInstr regA charIO                   
145: Load (ImmValue 111) regA                 
146: WriteInstr regA charIO                   
147: Load (ImmValue 102) regA                 
148: WriteInstr regA charIO                   
149: Load (ImmValue 32) regA                  
150: WriteInstr regA charIO                   
151: Load (ImmValue 98) regA                  
152: WriteInstr regA charIO                   
153: Load (ImmValue 111) regA                 
154: WriteInstr regA charIO                   
155: Load (ImmValue 117) regA                 
156: WriteInstr regA charIO                   
157: Load (ImmValue 110) regA                 
158: WriteInstr regA charIO                   
159: Load (ImmValue 100) regA                 
160: WriteInstr regA charIO                   
161: Load (ImmValue 115) regA                 
162: WriteInstr regA charIO                   
163: Load (ImmValue 32) regA                  
164: WriteInstr regA charIO                   
165: Load (ImmValue 105) regA                 
166: WriteInstr regA charIO                   
167: Load (ImmValue 110) regA                 
168: WriteInstr regA charIO                   
169: Load (ImmValue 100) regA                 
170: WriteInstr regA charIO                   
171: Load (ImmValue 101) regA                 
172: WriteInstr regA charIO                   
173: Load (ImmValue 120) regA                 
174: WriteInstr regA charIO                   
175: Load (ImmValue 32) regA                  
176: WriteInstr regA charIO                   
177: Load (ImmValue 111) regA                 
178: WriteInstr regA charIO                   
179: Load (ImmValue 102) regA                 
180: WriteInstr regA charIO                   
181: Load (ImmValue 32) regA                  
182: WriteInstr regA charIO                   
183: Load (ImmValue 97) regA                  
184: WriteInstr regA charIO                   
185: Load (ImmValue 114) regA                 
186: WriteInstr regA charIO                   
187: Load (ImmValue 114) regA                 
188: WriteInstr regA charIO                   
189: Load (ImmValue 97) regA                  
190: WriteInstr regA charIO                   
191: Load (ImmValue 121) regA                 
192: WriteInstr regA charIO                   
193: EndProg                        
``` 