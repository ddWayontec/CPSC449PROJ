module Main where
import System.IO  
import Control.Monad
import System.Environment
import Parser

main = do

  args <- getArgs
  contents <- readFile (head args)
  let linesOfContent = removeBlankLine (map removeEndSpace (map deleteR (lines contents)))

  putStr "linesOfContent: "
  putStrLn (show linesOfContent)

  --let nameSection = keepFromTo "" "forced partial assignment:" linesOfContent
  let forcedSection =  keepFromTo "forced partial assignment:" "forbidden machine:" linesOfContent
  let forbiddenSection = keepFromTo "forbidden machine:" "too-near tasks:" linesOfContent
  let tooNearTaskSection = keepFromTo "too-near tasks:" "machine penalties:" linesOfContent
  let machinePenSection = keepFromTo "machine penalties:" "too-near penalities" linesOfContent
  let tooNearPenSection = keepFromTo "too-near penalities" "" linesOfContent

  if (null machinePenSection) 
  then do (putStrLn "machine penalty error")
  else return()
  
  if not(isCorrectMachinePen machinePenSection)  || not((length machinePenSection) == 8)
  then do (putStrLn "machine penalty error")
  else return()
  
  if not(isAllPairs forcedSection) 
  then do (putStrLn "Error forced not in form (machine, task)")
  else return()
    
  if not(isAllPairs forbiddenSection)
  then do (putStrLn "Error forbidden not in form (machine, task)")
  else return()

  if not(isAllTriples tooNearPenSection)
  then do (putStrLn "Error too near penalty not in form (machine, task, penalty)")
  else return()
  
  if not(isValidPairMT forcedSection) || not(isValidPairMT forbiddenSection) || not(isValidPairTT tooNearTaskSection)
  then do (putStrLn "invalid machine/task")
  else return()
