### Output:
```
...
```

### Spril instructions:
```
  0: Load (ImmValue 1) regA                   --Assignment to b
  1: Store regA (DirAddr 0)                   
  2: Load (DirAddr 0) regA                    --(Loading var b) // While condition check
  3: Compute Equal regA reg0 regA             
  4: Branch regA (Abs 10)                     
  5: Load (ImmValue 1) regB                   --Assignment to b
  6: Load (ImmValue 1) regA                   
  7: Compute Equal regA regB regA             
  8: Store regA (DirAddr 0)                   
  9: Jump (Abs 2)                             
 10: EndProg                                  
 11: Load (ImmValue 0) regA                   --Error Handling
 12: Compute NEq regF regA regB               
 13: Branch regB (Rel 51)                     
 14: Load (ImmValue 69) regA                  --Print error DivByZero
 15: WriteInstr regA charIO                   
 16: Load (ImmValue 114) regA                 
 17: WriteInstr regA charIO                   
 18: Load (ImmValue 114) regA                 
 19: WriteInstr regA charIO                   
 20: Load (ImmValue 111) regA                 
 21: WriteInstr regA charIO                   
 22: Load (ImmValue 114) regA                 
 23: WriteInstr regA charIO                   
 24: Load (ImmValue 58) regA                  
 25: WriteInstr regA charIO                   
 26: Load (ImmValue 32) regA                  
 27: WriteInstr regA charIO                   
 28: Load (ImmValue 67) regA                  
 29: WriteInstr regA charIO                   
 30: Load (ImmValue 97) regA                  
 31: WriteInstr regA charIO                   
 32: Load (ImmValue 110) regA                 
 33: WriteInstr regA charIO                   
 34: Load (ImmValue 110) regA                 
 35: WriteInstr regA charIO                   
 36: Load (ImmValue 111) regA                 
 37: WriteInstr regA charIO                   
 38: Load (ImmValue 116) regA                 
 39: WriteInstr regA charIO                   
 40: Load (ImmValue 32) regA                  
 41: WriteInstr regA charIO                   
 42: Load (ImmValue 100) regA                 
 43: WriteInstr regA charIO                   
 44: Load (ImmValue 105) regA                 
 45: WriteInstr regA charIO                   
 46: Load (ImmValue 118) regA                 
 47: WriteInstr regA charIO                   
 48: Load (ImmValue 105) regA                 
 49: WriteInstr regA charIO                   
 50: Load (ImmValue 100) regA                 
 51: WriteInstr regA charIO                   
 52: Load (ImmValue 101) regA                 
 53: WriteInstr regA charIO                   
 54: Load (ImmValue 32) regA                  
 55: WriteInstr regA charIO                   
 56: Load (ImmValue 98) regA                  
 57: WriteInstr regA charIO                   
 58: Load (ImmValue 121) regA                 
 59: WriteInstr regA charIO                   
 60: Load (ImmValue 32) regA                  
 61: WriteInstr regA charIO                   
 62: Load (ImmValue 48) regA                  
 63: WriteInstr regA charIO                   
 64: Load (ImmValue 1) regA                   
 65: Compute NEq regF regA regB               
 66: Branch regB (Rel 113)                    
 67: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
 68: WriteInstr regA charIO                   
 69: Load (ImmValue 114) regA                 
 70: WriteInstr regA charIO                   
 71: Load (ImmValue 114) regA                 
 72: WriteInstr regA charIO                   
 73: Load (ImmValue 111) regA                 
 74: WriteInstr regA charIO                   
 75: Load (ImmValue 114) regA                 
 76: WriteInstr regA charIO                   
 77: Load (ImmValue 58) regA                  
 78: WriteInstr regA charIO                   
 79: Load (ImmValue 32) regA                  
 80: WriteInstr regA charIO                   
 81: Load (ImmValue 65) regA                  
 82: WriteInstr regA charIO                   
 83: Load (ImmValue 116) regA                 
 84: WriteInstr regA charIO                   
 85: Load (ImmValue 116) regA                 
 86: WriteInstr regA charIO                   
 87: Load (ImmValue 101) regA                 
 88: WriteInstr regA charIO                   
 89: Load (ImmValue 109) regA                 
 90: WriteInstr regA charIO                   
 91: Load (ImmValue 112) regA                 
 92: WriteInstr regA charIO                   
 93: Load (ImmValue 116) regA                 
 94: WriteInstr regA charIO                   
 95: Load (ImmValue 105) regA                 
 96: WriteInstr regA charIO                   
 97: Load (ImmValue 110) regA                 
 98: WriteInstr regA charIO                   
 99: Load (ImmValue 103) regA                 
100: WriteInstr regA charIO                   
101: Load (ImmValue 32) regA                  
102: WriteInstr regA charIO                   
103: Load (ImmValue 116) regA                 
104: WriteInstr regA charIO                   
105: Load (ImmValue 111) regA                 
106: WriteInstr regA charIO                   
107: Load (ImmValue 32) regA                  
108: WriteInstr regA charIO                   
109: Load (ImmValue 97) regA                  
110: WriteInstr regA charIO                   
111: Load (ImmValue 99) regA                  
112: WriteInstr regA charIO                   
113: Load (ImmValue 99) regA                  
114: WriteInstr regA charIO                   
115: Load (ImmValue 101) regA                 
116: WriteInstr regA charIO                   
117: Load (ImmValue 115) regA                 
118: WriteInstr regA charIO                   
119: Load (ImmValue 115) regA                 
120: WriteInstr regA charIO                   
121: Load (ImmValue 32) regA                  
122: WriteInstr regA charIO                   
123: Load (ImmValue 111) regA                 
124: WriteInstr regA charIO                   
125: Load (ImmValue 117) regA                 
126: WriteInstr regA charIO                   
127: Load (ImmValue 116) regA                 
128: WriteInstr regA charIO                   
129: Load (ImmValue 32) regA                  
130: WriteInstr regA charIO                   
131: Load (ImmValue 111) regA                 
132: WriteInstr regA charIO                   
133: Load (ImmValue 102) regA                 
134: WriteInstr regA charIO                   
135: Load (ImmValue 32) regA                  
136: WriteInstr regA charIO                   
137: Load (ImmValue 98) regA                  
138: WriteInstr regA charIO                   
139: Load (ImmValue 111) regA                 
140: WriteInstr regA charIO                   
141: Load (ImmValue 117) regA                 
142: WriteInstr regA charIO                   
143: Load (ImmValue 110) regA                 
144: WriteInstr regA charIO                   
145: Load (ImmValue 100) regA                 
146: WriteInstr regA charIO                   
147: Load (ImmValue 115) regA                 
148: WriteInstr regA charIO                   
149: Load (ImmValue 32) regA                  
150: WriteInstr regA charIO                   
151: Load (ImmValue 105) regA                 
152: WriteInstr regA charIO                   
153: Load (ImmValue 110) regA                 
154: WriteInstr regA charIO                   
155: Load (ImmValue 100) regA                 
156: WriteInstr regA charIO                   
157: Load (ImmValue 101) regA                 
158: WriteInstr regA charIO                   
159: Load (ImmValue 120) regA                 
160: WriteInstr regA charIO                   
161: Load (ImmValue 32) regA                  
162: WriteInstr regA charIO                   
163: Load (ImmValue 111) regA                 
164: WriteInstr regA charIO                   
165: Load (ImmValue 102) regA                 
166: WriteInstr regA charIO                   
167: Load (ImmValue 32) regA                  
168: WriteInstr regA charIO                   
169: Load (ImmValue 97) regA                  
170: WriteInstr regA charIO                   
171: Load (ImmValue 114) regA                 
172: WriteInstr regA charIO                   
173: Load (ImmValue 114) regA                 
174: WriteInstr regA charIO                   
175: Load (ImmValue 97) regA                  
176: WriteInstr regA charIO                   
177: Load (ImmValue 121) regA                 
178: WriteInstr regA charIO                   
179: EndProg          
``` 