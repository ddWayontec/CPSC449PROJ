module Main where
import System.IO  
import Control.Monad
import System.Environment
import Parser

main = do

  args <- getArgs
  contents <- readFile (head args)
  --let linesOfContent = removeBlankLine (lines (removeEndSpace (deleteR contents)))
  let linesOfContent = removeBlankLine (map removeEndSpace (map deleteR (lines contents)))

  putStr "linesOfContent: "
  putStrLn (show linesOfContent)

  let nameSection = keepFromTo "Name:" "forced partial assignment:" linesOfContent
  let forcedSection =  keepFromTo "forced partial assignment:" "forbidden machine:" linesOfContent
  let forbiddenSection = keepFromTo "forbidden machine:" "too-near tasks:" linesOfContent
  let tooNearTaskSection = keepFromTo "too-near tasks:" "machine penalties:" linesOfContent
  let machinePenSection = keepFromTo "machine penalties:" "too-near penalities" linesOfContent
  let tooNearPenSection = keepFromTo "too-near penalities" "" linesOfContent



  putStr "name: "
  putStrLn (show nameSection)
  putStrLn "done"


