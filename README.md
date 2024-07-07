# CfgFirstFollow-Task-6

## German University in Cairo
### Department of Computer Science
### Assoc. Prof. Haythem O. Ismail

### CSEN1002 Compilers Lab, Spring Term 2024
**Task 6: Context-Free Grammars First and Follow**

## Overview
This project involves implementing algorithms to compute the First and Follow sets for the variables of a given context-free grammar (CFG). A CFG is defined as a quadruple (V, Σ, R, S) where V and Σ are disjoint alphabets (containing variables and terminals, respectively), R ⊆ V × (V ∪ Σ)* is a set of rules, and S ∈ V is the start variable.

## Objective
Implement the algorithms for computing the First and Follow sets for a given CFG. The project includes:
- Defining a class constructor `CfgFirstFollow`.
- Implementing methods `first` and `follow`.

## Requirements

1. **Assumptions:**
   - The set V of variables consists of upper-case English letters.
   - The start variable is the symbol S.
   - The set Σ of terminals consists of lower-case English letters (excluding 'e').
   - The letter "e" represents ε.

2. **Implementation:**
   - **Class Constructor `CfgFirstFollow`:**
     - Takes a single parameter, a string description of a CFG in the format `V#T#R`.
     - Example CFG: G1 = ({S, T, L}, {a, b, c, d, i}, R, S)
       - R: S → ScT | T
              T → aSb | iaLb | ε
              L → SdL | S
       - Encoded as: `S; T;L#a;b;c;d;i#S/ScT,T;T/aSb,iaLb,e;L/SdL,S`

   - **Method `first`:**
     - Computes the First set for each variable in the CFG.
     - Returns a semi-colon-separated sequence of items, where each item is a /-separated pair. The first element is a variable, and the second element is a string representing the First set of that variable.

   - **Method `follow`:**
     - Computes the Follow set for each variable in the CFG.
     - Returns a semi-colon-separated sequence of items, where each item is a /-separated pair. The first element is a variable, and the second element is a string representing the Follow set of that variable. ($ always appears first in the Follow set.)

   - **Output Format:**
     - The output of each method should be in alphabetical order.
     - Example output for `first` on G1: `S/acei;T/aei;L/acdei`
     - Example output for `follow` on G1: `S/$bcd;T/$bcd;L/b`

For any further details or clarifications, refer to the lab manual or contact the course instructor.
