### Output with join statement:
```
Sprockell 1 says 5
Sprockell 0 says 6
```

### Output without join:
```
Sprockell 0 says 6
Sprockell 1 says 5
```

### Spril instructions:
```
  0: Branch regSprID (Abs 11)                 --Make all threads other than main jump to the idle loop
  1: Load (ImmValue 6) regA                   --Fork to thread1
  2: WriteInstr regA (DirAddr 0)              
  3: Load (ImmValue 6) regA                   --Print
  4: WriteInstr regA numberIO                 
  5: Jump (Abs 21)                            
  6: Load (ImmValue 5) regA                   --Print // Start of thread thread1
  7: WriteInstr regA numberIO                 
  8: Load (ImmValue (-1)) regA                --Set thread_done flag for join(thread1)
  9: WriteInstr regA (DirAddr 0)              
 10: Jump (Abs 21)                            
 11: Load (ImmValue 1) regA                   --Get thread memory location for thread 1
 12: Compute NEq regA regSprID regA           
 13: Branch regA (Rel 3)                      
 14: Load (ImmValue 0) regA                   
 15: Jump (Abs 16)                            
 16: ReadInstr (IndAddr regA)                 --Thread pool idle loop
 17: Receive regB                             
 18: Compute Equal regB reg0 regC             
 19: Branch regC (Rel (-3))                   
 20: Jump (Ind regB)                          
 21: EndProg                                  
 22: Load (ImmValue 0) regA                   --Error Handling
 23: Compute NEq regF regA regB               
 24: Branch regB (Rel 51)                     
 25: Load (ImmValue 69) regA                  --Print error DivByZero
 26: WriteInstr regA charIO                   
 27: Load (ImmValue 114) regA                 
 28: WriteInstr regA charIO                   
 29: Load (ImmValue 114) regA                 
 30: WriteInstr regA charIO                   
 31: Load (ImmValue 111) regA                 
 32: WriteInstr regA charIO                   
 33: Load (ImmValue 114) regA                 
 34: WriteInstr regA charIO                   
 35: Load (ImmValue 58) regA                  
 36: WriteInstr regA charIO                   
 37: Load (ImmValue 32) regA                  
 38: WriteInstr regA charIO                   
 39: Load (ImmValue 67) regA                  
 40: WriteInstr regA charIO                   
 41: Load (ImmValue 97) regA                  
 42: WriteInstr regA charIO                   
 43: Load (ImmValue 110) regA                 
 44: WriteInstr regA charIO                   
 45: Load (ImmValue 110) regA                 
 46: WriteInstr regA charIO                   
 47: Load (ImmValue 111) regA                 
 48: WriteInstr regA charIO                   
 49: Load (ImmValue 116) regA                 
 50: WriteInstr regA charIO                   
 51: Load (ImmValue 32) regA                  
 52: WriteInstr regA charIO                   
 53: Load (ImmValue 100) regA                 
 54: WriteInstr regA charIO                   
 55: Load (ImmValue 105) regA                 
 56: WriteInstr regA charIO                   
 57: Load (ImmValue 118) regA                 
 58: WriteInstr regA charIO                   
 59: Load (ImmValue 105) regA                 
 60: WriteInstr regA charIO                   
 61: Load (ImmValue 100) regA                 
 62: WriteInstr regA charIO                   
 63: Load (ImmValue 101) regA                 
 64: WriteInstr regA charIO                   
 65: Load (ImmValue 32) regA                  
 66: WriteInstr regA charIO                   
 67: Load (ImmValue 98) regA                  
 68: WriteInstr regA charIO                   
 69: Load (ImmValue 121) regA                 
 70: WriteInstr regA charIO                   
 71: Load (ImmValue 32) regA                  
 72: WriteInstr regA charIO                   
 73: Load (ImmValue 48) regA                  
 74: WriteInstr regA charIO                   
 75: Load (ImmValue 1) regA                   
 76: Compute NEq regF regA regB               
 77: Branch regB (Rel 113)                    
 78: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
 79: WriteInstr regA charIO                   
 80: Load (ImmValue 114) regA                 
 81: WriteInstr regA charIO                   
 82: Load (ImmValue 114) regA                 
 83: WriteInstr regA charIO                   
 84: Load (ImmValue 111) regA                 
 85: WriteInstr regA charIO                   
 86: Load (ImmValue 114) regA                 
 87: WriteInstr regA charIO                   
 88: Load (ImmValue 58) regA                  
 89: WriteInstr regA charIO                   
 90: Load (ImmValue 32) regA                  
 91: WriteInstr regA charIO                   
 92: Load (ImmValue 65) regA                  
 93: WriteInstr regA charIO                   
 94: Load (ImmValue 116) regA                 
 95: WriteInstr regA charIO                   
 96: Load (ImmValue 116) regA                 
 97: WriteInstr regA charIO                   
 98: Load (ImmValue 101) regA                 
 99: WriteInstr regA charIO                   
100: Load (ImmValue 109) regA                 
101: WriteInstr regA charIO                   
102: Load (ImmValue 112) regA                 
103: WriteInstr regA charIO                   
104: Load (ImmValue 116) regA                 
105: WriteInstr regA charIO                   
106: Load (ImmValue 105) regA                 
107: WriteInstr regA charIO                   
108: Load (ImmValue 110) regA                 
109: WriteInstr regA charIO                   
110: Load (ImmValue 103) regA                 
111: WriteInstr regA charIO                   
112: Load (ImmValue 32) regA                  
113: WriteInstr regA charIO                   
114: Load (ImmValue 116) regA                 
115: WriteInstr regA charIO                   
116: Load (ImmValue 111) regA                 
117: WriteInstr regA charIO                   
118: Load (ImmValue 32) regA                  
119: WriteInstr regA charIO                   
120: Load (ImmValue 97) regA                  
121: WriteInstr regA charIO                   
122: Load (ImmValue 99) regA                  
123: WriteInstr regA charIO                   
124: Load (ImmValue 99) regA                  
125: WriteInstr regA charIO                   
126: Load (ImmValue 101) regA                 
127: WriteInstr regA charIO                   
128: Load (ImmValue 115) regA                 
129: WriteInstr regA charIO                   
130: Load (ImmValue 115) regA                 
131: WriteInstr regA charIO                   
132: Load (ImmValue 32) regA                  
133: WriteInstr regA charIO                   
134: Load (ImmValue 111) regA                 
135: WriteInstr regA charIO                   
136: Load (ImmValue 117) regA                 
137: WriteInstr regA charIO                   
138: Load (ImmValue 116) regA                 
139: WriteInstr regA charIO                   
140: Load (ImmValue 32) regA                  
141: WriteInstr regA charIO                   
142: Load (ImmValue 111) regA                 
143: WriteInstr regA charIO                   
144: Load (ImmValue 102) regA                 
145: WriteInstr regA charIO                   
146: Load (ImmValue 32) regA                  
147: WriteInstr regA charIO                   
148: Load (ImmValue 98) regA                  
149: WriteInstr regA charIO                   
150: Load (ImmValue 111) regA                 
151: WriteInstr regA charIO                   
152: Load (ImmValue 117) regA                 
153: WriteInstr regA charIO                   
154: Load (ImmValue 110) regA                 
155: WriteInstr regA charIO                   
156: Load (ImmValue 100) regA                 
157: WriteInstr regA charIO                   
158: Load (ImmValue 115) regA                 
159: WriteInstr regA charIO                   
160: Load (ImmValue 32) regA                  
161: WriteInstr regA charIO                   
162: Load (ImmValue 105) regA                 
163: WriteInstr regA charIO                   
164: Load (ImmValue 110) regA                 
165: WriteInstr regA charIO                   
166: Load (ImmValue 100) regA                 
167: WriteInstr regA charIO                   
168: Load (ImmValue 101) regA                 
169: WriteInstr regA charIO                   
170: Load (ImmValue 120) regA                 
171: WriteInstr regA charIO                   
172: Load (ImmValue 32) regA                  
173: WriteInstr regA charIO                   
174: Load (ImmValue 111) regA                 
175: WriteInstr regA charIO                   
176: Load (ImmValue 102) regA                 
177: WriteInstr regA charIO                   
178: Load (ImmValue 32) regA                  
179: WriteInstr regA charIO                   
180: Load (ImmValue 97) regA                  
181: WriteInstr regA charIO                   
182: Load (ImmValue 114) regA                 
183: WriteInstr regA charIO                   
184: Load (ImmValue 114) regA                 
185: WriteInstr regA charIO                   
186: Load (ImmValue 97) regA                  
187: WriteInstr regA charIO                   
188: Load (ImmValue 121) regA                 
189: WriteInstr regA charIO                   
190: EndProg                  
        
``` 